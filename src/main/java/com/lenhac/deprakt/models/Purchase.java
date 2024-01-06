package com.lenhac.deprakt.models;

//@Entity
//@Table(name = "purchases")
//@Data
public class Purchase extends Base {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "purchase_id", updatable = false, nullable = false)
//    private Long id;
//
//    @ManyToOne // Many-to-one relationship with Asset (asset purchased in the purchase)
//    @JoinColumn(name = "asset_id", nullable = false)
//    private Asset asset;
//
//    @ManyToOne // Many-to-one relationship with ServiceProvider (service provider from whom the asset is purchased)
//    @JoinColumn(name = "service_provider_id", nullable = false)
//    private ServiceProvider serviceProvider;
//
//    @ManyToOne // Many-to-one relationship with Employee (employee who requested the purchase)
//    @JoinColumn(name = "requested_by_id", nullable = false)
//    private Employee requestedBy;
//
//    @ManyToOne // Many-to-one relationship with Employee (employee who approved the purchase)
//    @JoinColumn(name = "approved_by_id")
//    private Employee approvedBy;
//
//    @Column(name = "purchase_date")
//    private LocalDate purchaseDate;
//
//    @Column(name = "purchase_cost")
//    private Double purchaseCost;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "expected_date")
//    private LocalDate expectedDate;
//
//    @ManyToOne // Many-to-one relationship with Department (department associated with the purchase)
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//    // Constructors, getters, setters, and other methods...
//
//    // Constructor without arguments
//    public Purchase() {
//    }
//
//    // Constructor with asset, serviceProvider, requestedBy, approvedBy, purchaseDate, purchaseCost, description,
//    // expectedDate, department, and quantity
//    public Purchase(Asset asset, ServiceProvider serviceProvider, Employee requestedBy, Employee approvedBy,
//                    LocalDate purchaseDate, Double purchaseCost, String description, LocalDate expectedDate,
//                    Department department, Integer quantity) {
//        this.asset = asset;
//        this.serviceProvider = serviceProvider;
//        this.requestedBy = requestedBy;
//        this.approvedBy = approvedBy;
//        this.purchaseDate = purchaseDate;
//        this.purchaseCost = purchaseCost;
//        this.description = description;
//        this.expectedDate = expectedDate;
//        this.department = department;
//        this.quantity = quantity;
//    }
//
//    // Getters and setters for id, asset, serviceProvider, requestedBy, approvedBy, purchaseDate, purchaseCost,
//    // description, expectedDate, department, and quantity...
//
//    // Override toString() method for better representation
//    @Override
//    public String toString() {
//        return "Purchase{" +
//                "id=" + id +
//                ", purchaseDate=" + purchaseDate +
//                ", purchaseCost=" + purchaseCost +
//                '}';
//    }
}
