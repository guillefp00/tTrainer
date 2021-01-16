package com.upc.ittrainer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModelEntityTest {

    private ModelEntityImp modelEntityImp;


    @Before
    public void setUp() {
        modelEntityImp = ModelEntityImp.create();
    }

    @Test
    public void getID() {
        Assert.assertEquals(ModelEntityImp.ID_TEST, modelEntityImp.getID());
    }

    @Test
    public void isNew() {
        Assert.assertFalse(modelEntityImp.isNew());
        modelEntityImp = new ModelEntityImp();
        Assert.assertTrue(modelEntityImp.isNew());
    }

}