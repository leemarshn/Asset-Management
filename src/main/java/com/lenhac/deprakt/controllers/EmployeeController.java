package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.Department;
import com.lenhac.deprakt.models.Employee;
import com.lenhac.deprakt.models.Person;
import com.lenhac.deprakt.models.Status;
import com.lenhac.deprakt.repositories.DepartmentRepository;
import com.lenhac.deprakt.repositories.EmployeeRepository;
import com.lenhac.deprakt.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository employeeRepository, PersonRepository personRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/organization/employee/new")
    public String showEmployeeForm(Model model) {
        List<Department> allDepartments = departmentRepository.findAll();  // Use the repository to fetch departments
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("employeeForm", new Employee());
//        model.addAttribute("personForm", new Person());

        return "employeeForm";
    }

    @PostMapping("/organization/employee/new")
    @Transactional
    public String createEmployee(
            @Valid @ModelAttribute("employeeForm") Employee employeeForm,
//            @Valid @ModelAttribute("personForm") Person personForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Error creating employee: Please check the form for errors.");
            return "redirect:/organization/employee/new";
        }

        try {
            Person person = new Person();
            person.setFirstName(employeeForm.getPersonForm().getFirstName());
            person.setLastName(employeeForm.getPersonForm().getLastName());
            person.setEmail(employeeForm.getPersonForm().getEmail());
            person.setDateOfBirth(employeeForm.getPersonForm().getDateOfBirth());
            person.setPhoneNumber(employeeForm.getPersonForm().getPhoneNumber());
            person.setDetails(employeeForm.getPersonForm().getDetails());


           person = personRepository.save(person);

            // Now save the employee using the person's ID
            Employee employee = new Employee();
//            employee.setId(person.getId());  // Use the same ID for person and employee
            employee.setNationalId(employeeForm.getNationalId());
            employee.setEmploymentStartDate(employeeForm.getEmploymentStartDate());
            employee.setPosition(employeeForm.getPosition());
            employee.setPersonForm(person);

            employeeRepository.save(employee);

            redirectAttributes.addFlashAttribute("success", "Employee created successfully!");
            return "redirect:/organization/employee/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating employee: " + e.getMessage());
            return "redirect:/organization/employee/new";
        }
    }



//    @GetMapping("/organization/employees")
//    public String showEmployees(Model model) {
//
//        List<Employee> employees = employeeRepository.findAll();  // Replace with your service call
//
//        // Fetch only necessary fields and include role name
//        List<EmployeeDTO> employeeDTOs = employees.stream()
//                .map(employee -> new EmployeeDTO(
//                        employee.getId(),
//                        employee.getFirstName().concat(" ").concat(employee.getLastName()),
//                        employee.getEmail(),
//                        employee.getPhoneNumber(),
//                        employee.getPosition(),
//                        employee.getDepartment().getName()
//                          // Include role name
//                ))
//                .collect(Collectors.toList());
//
//        model.addAttribute("employees", employeeDTOs);
//        return "employees";  // Replace with the name of your employee view template
//    }

/**
 * BEGINNING OF DEPARTMENT CONTROLLERS
 */
    @GetMapping("/organization/department/new")
    public String newDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments";
    }

    @PostMapping("/organization/department/new")
    public String createDepartment(@ModelAttribute Department department, Model model) {
        departmentRepository.save(department);
        model.addAttribute("success", "Department added successfully!");
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments";
    }

    @GetMapping("/organization/departments")
    public String viewDepartments(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments";
    }











}
