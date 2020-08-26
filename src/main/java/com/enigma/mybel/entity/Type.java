package com.enigma.mybel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "type")
@Getter @Setter
@ToString
public class Type {
    @Id
    @GenericGenerator(name = "type_uuid",strategy = "uuid")
    @GeneratedValue(generator = "type_uuid")
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_room")
    @JsonIgnoreProperties(value = {"types","designInteriors"})
    private CategoryRoom room;

    @OneToMany(mappedBy = "type")
    @JsonIgnoreProperties("type")
    private List<Unit> units;

    public Type(String name) {
        this.name = name;
    }
    public Type() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id) &&
                Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
