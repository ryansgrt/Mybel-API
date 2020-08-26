package com.enigma.mybel.entity;

import com.enigma.mybel.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vendor")
@Getter @Setter
@ToString
public class Vendor {
    @Id
    @GeneratedValue( generator = "vendor_uuid")
    @GenericGenerator(name = "vendor_uuid",strategy = "uuid")
    private String id;
    private String name;
    private String username;
    private String password;
    private Gender gender;
    private String company;
    private String email;
    private String photo;
    private String address;
    private Boolean statusRequest;

    @OneToMany(mappedBy = "vendor")
    @JsonIgnoreProperties(value = {"type","vendor"})
//    @JsonIgnore
    private List<Unit> units;

    @OneToMany(mappedBy = "vendor")
//    @JsonIgnore
    private List<DesignInterior> designInteriors;

    public Vendor(String name,String username,String password,Gender gender,String company,String email,String address){
        this.name=name;
        this.username=username;
        this.password=password;
        this.gender=gender;
        this.company=company;
        this.email=email;
        this.address=address;
        this.statusRequest=false;

    }

    public Vendor() {
        this.statusRequest=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(id, vendor.id) &&
                Objects.equals(name, vendor.name) &&
                Objects.equals(username, vendor.username) &&
                Objects.equals(password, vendor.password) &&
                gender == vendor.gender &&
                Objects.equals(company, vendor.company) &&
                Objects.equals(email, vendor.email) &&
                Objects.equals(photo, vendor.photo) &&
                Objects.equals(address, vendor.address) &&
                Objects.equals(statusRequest, vendor.statusRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, gender, company, email, photo, address, statusRequest);
    }
}
