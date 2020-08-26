package com.enigma.mybel.entity;

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
@Table(name = "user")
@Getter @Setter
@ToString
public class User {
    @Id
    @GenericGenerator(name = "user_uuid",strategy = "uuid")
    @GeneratedValue(generator = "user_uuid")
    private String id;
    private String name;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String photo;
    private Boolean status;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Transaction> transactions;

    public User(String name, String username, String email, String address, String phone, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.status=false;
    }

    public User() {
        this.status=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(password, user.password) &&
                Objects.equals(photo, user.photo) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, address, phone, password, photo, status);
    }
}