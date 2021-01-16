/*
 * AppUser.java
 */
package com.upc.ittrainer.model.admin;

import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.model.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AppUser implements ModelEntity<String>, Comparable<AppUser> {

    @Id
    @Column
    @NotEmpty
    @EqualsAndHashCode.Include
    private String name;

    @Column
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    private String password;

    @Column
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_name"), inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy="appUser")
    private Set<Client> clients;

    public AppUser(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        this.clients = new HashSet<>();
    }

    public AppUser() {
        roles = new HashSet<>();
        enabled = true;
    }

    public String printRoles() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Role role : roles) {
            stringBuilder.append(role.getName()).append(" - ");
        }
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(AppUser o) {
        return this.name.compareTo(o.name);
    }

    @Transient
    @Override
    public String getID() {
        return getName();
    }

}
