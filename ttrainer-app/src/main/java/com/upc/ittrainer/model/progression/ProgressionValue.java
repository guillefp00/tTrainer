package com.upc.ittrainer.model.progression;

import com.upc.ittrainer.model.ModelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "progression_value")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProgressionValue implements ModelEntity<Integer> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "progression_id")
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Progression progression;

    @ManyToOne
    @JoinColumn(name = "scope_name")
    @EqualsAndHashCode.Include
    private Scope scope;
    @Column
    private Double value;

    public ProgressionValue(Progression progression, Scope scope, Double value) {
        this.progression = progression;
        this.scope = scope;
        this.value = value;
    }

    @Transient
    @Override
    public Integer getID() {
        return id;
    }

    public Integer getId() {
        return id;
    }
}
