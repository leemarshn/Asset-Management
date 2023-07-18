package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.PermissionEntity;
import com.lenhac.deprakt.repositories.PermissionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private PermissionRepository permissionRepository;

    public UserController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostMapping("/permissions")
    public void savePermission(@RequestBody PermissionEntity permissionEntity) {
        permissionRepository.save(permissionEntity);
    }

    @GetMapping("/permissions")
    public List<PermissionEntity> getAllPermissions() {
        return permissionRepository.findAll();
    }
}

