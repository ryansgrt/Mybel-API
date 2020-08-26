package com.enigma.mybel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
@Getter @Setter
public class Unit {
    @Id
    @GenericGenerator(name = "unit_uuid",strategy = "uuid")
    @GeneratedValue(generator = "unit_uuid")
    private String id;
    private String name;
    private Double price;
    private String description;
    private String picture;

    @Transient
    @JsonBackReference("vendor")
    private String idVendor;

    @Transient
    @JsonBackReference("type")
    private String idType;

    @ManyToOne
    @JoinColumn(name = "id_type")
    @JsonIgnoreProperties("types")
    private Type type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_vendor")
    private Vendor vendor;

    @ManyToMany
    @JoinTable(name = "unit_has_transaction",
            joinColumns = {@JoinColumn(name = "id_unit")},
            inverseJoinColumns = {@JoinColumn(name = "id_transaction")})
    @JsonIgnore
    private List<Transaction> transactions;

    public Unit(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Unit() {
    }
}