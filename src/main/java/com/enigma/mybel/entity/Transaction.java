package com.enigma.mybel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
@Getter @Setter
public class Transaction {
    @Id
    @GenericGenerator(name = "trx_uuid",strategy = "uuid")
    @GeneratedValue(generator = "trx_uuid")
    private String id;
    private Integer quantity;
    private Date date;
    private String sendLocation;
    private Double totalPrice;

    @Transient
    @JsonBackReference("selectedUnit")
    private String unit;

    @Transient
    @JsonBackReference("selectedDesign")
    private String design;

    @Transient
    @JsonBackReference("userTransaction")
    private String selectUser;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("transactions")
    private User user;

    @ManyToMany(mappedBy = "transactions")
    @JsonIgnoreProperties(value = {"transactions","vendor","type"})
    private List<Unit> units;

    @ManyToMany(mappedBy = "transactions")
    @JsonIgnoreProperties(value = {"room","vendor","transactions"})
    private List<DesignInterior> designInteriors;

    public Transaction() {
        this.date=new Date();
    }

    public Transaction(Integer quantity,String sendLocation,Double totalPrice){
        this.quantity = quantity;
        this.sendLocation=sendLocation;
        this.totalPrice=totalPrice;
        this.date=new Date();
    }

}
