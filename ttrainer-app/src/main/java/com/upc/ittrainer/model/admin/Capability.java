package com.upc.ittrainer.model.admin;

import com.upc.ittrainer.model.ModelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "capability")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Capability implements ModelEntity<String>, Comparable<Capability> {

    @Id
    @Column(name = "name")
    @NotEmpty
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @Column(name = "pattern")
    @NotEmpty
    @ToString.Include
    private String pattern;

    public Capability(String name) {
        this.name = name;
    }

    @Override
    public String getID() {
        return getName();
    }

    @Override
    public int compareTo(Capability o) {
        return this.name.compareTo(o.name);
    }

}