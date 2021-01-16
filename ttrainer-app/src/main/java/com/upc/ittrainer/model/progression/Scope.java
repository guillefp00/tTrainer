package com.upc.ittrainer.model.progression;

import com.upc.ittrainer.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scope")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scope implements ModelEntity<String> {

    @Id
    @Column
    private String name;

    @Override
    public String getID() {
        return name;
    }
}
