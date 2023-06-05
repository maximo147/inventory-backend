package com.company.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private Double price;
    @Column(nullable = false)
    private Integer quantity;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", length = 1000)
    private byte[] picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "category_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

}
