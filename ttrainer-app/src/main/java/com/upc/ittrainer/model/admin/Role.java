package com.upc.ittrainer.model.admin;

import com.upc.ittrainer.model.ModelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Role implements ModelEntity<String>, Comparable<Role> {

    @Id
    @Column(name = "name")
    @NotEmpty
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "role_capability", joinColumns = @JoinColumn(name = "role_name"), inverseJoinColumns = @JoinColumn(name = "capability_name"))
    @ToString.Include
    private Set<Capability> capabilities;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "role_write_capability", joinColumns = @JoinColumn(name = "role_name"), inverseJoinColumns = @JoinColumn(name = "capability_name"))
    @ToString.Include
    private Set<Capability> writeCapabilities;

    public Role() {
        capabilities = new HashSet<>();
    }

    @Override
    public String getID() {
        return getName();
    }

    @Override
    public int compareTo(Role o) {
        return this.name.compareTo(o.name);
    }

}
