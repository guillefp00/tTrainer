package com.upc.ittrainer.model.progression;

import com.google.common.primitives.Doubles;
import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.model.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "progression")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Progression implements ModelEntity<Integer>, Comparable<Progression> {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "progression")
    private Set<ProgressionValue> progressionValues;

    public Progression() {
        progressionValues = new HashSet<>();
    }

    @Transient
    @Override
    public Integer getID() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Progression o) {
        return date.compareTo(o.date);
    }
}
