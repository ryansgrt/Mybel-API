package com.enigma.mybel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "design_interior")
@Getter @Setter
public class DesignInterior {
    @Id
    @GeneratedValue(generator = "design_uuid")
    @GenericGenerator(name = "design_uuid",strategy = "uuid")
    private String id;
    private String theme;
    private Double price;
    private String picture;
    private String description;
    private Integer duration;

    @Transient
    @JsonBackReference("room")
    private String roomName;

    @Transient
    @JsonBackReference("vendor")
    private String idVendor;

    @ManyToOne
    @JoinColumn(name = "id_room")
    @JsonIgnoreProperties(value = {"types","designInteriors"})
    private CategoryRoom room;

    @ManyToOne
    @JoinColumn(name = "id_vendor")
    @JsonIgnore
    private Vendor vendor;

    @ManyToMany
    @JoinTable(name = "design_has_transaction",
            joinColumns = {@JoinColumn(name = "id_design")},
            inverseJoinColumns ={@JoinColumn(name = "id_transaction")})
    @JsonIgnore
    private List<Transaction> transactions;

    public DesignInterior() {
    }
    public DesignInterior(String theme, Double price, String description,Integer duration) {
        this.theme=theme;
        this.price=price;
        this.description=description;
        this.duration=duration;
    }
}
