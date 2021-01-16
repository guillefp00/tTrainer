package com.upc.ittrainer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public interface ModelEntity<ID> extends Serializable {

    @JsonIgnore
    ID getID();

    @JsonIgnore
    default boolean isNew() {
        return getID() == null;
    }

}
