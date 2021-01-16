package com.upc.ittrainer.view.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringsMBTest {

    private StringsMB stringsMB;

    @Before
    public void setUp() {
        stringsMB = new StringsMB();
    }

    @Test
    public void capitalize() {
        final String testString = "to_capitalize_string";
        final String expString = "To capitalize string";

        Assert.assertEquals(expString, stringsMB.capitalize(testString));
    }
}