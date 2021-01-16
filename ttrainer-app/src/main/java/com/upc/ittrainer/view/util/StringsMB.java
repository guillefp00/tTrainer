package com.upc.ittrainer.view.util;

import org.omnifaces.el.functions.Strings;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class StringsMB implements Serializable {

    public String capitalize(final String text) {
        return Strings.capitalize(text.replaceAll("_", " "));
    }

    public String printList(final List<?> list) {
        return list == null ? "" :
                list.stream().map(Object::toString).collect(Collectors.joining(","));
    }

}