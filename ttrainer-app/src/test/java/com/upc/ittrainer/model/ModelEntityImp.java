package com.upc.ittrainer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelEntityImp implements ModelEntity<Integer> {

    private Integer id;
    @NotEmpty
    private String field;

    public static final Integer ID_TEST = 1;
    public static final String FIELD_TEST = "test";

    @Override
    public Integer getID() {
        return id;
    }

    public static ModelEntityImp create() {
        return new ModelEntityImp(ID_TEST, FIELD_TEST);
    }

}