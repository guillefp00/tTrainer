package com.upc.ittrainer.view.util;

import com.google.common.base.Strings;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@ViewScoped
public class FilterMB implements Serializable {

    public boolean filter(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (Strings.isNullOrEmpty(filterText)) {
            return true;
        }
        if (value == null) {
            return false;
        }
        return value.toString().contains(filterText);
    }

}
