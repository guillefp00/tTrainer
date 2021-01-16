package com.upc.ittrainer.view;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.model.ModelEntityImp;
import com.upc.ittrainer.service.ServiceImp;
import com.upc.ittrainer.util.MessagesBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.omnifaces.util.Faces;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.ToggleSelectEvent;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Faces.class, MessagesBean.class})
public class EntitiesMBTest {

    private EntitiesImpMB entitiesMB;
    private ServiceImp service;
    private ModelEntityImp entityImp;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Faces.class);
        PowerMockito.mockStatic(MessagesBean.class);
        service = Mockito.mock(ServiceImp.class);
        entitiesMB = new EntitiesImpMB(service);
        entityImp = ModelEntityImp.create();
    }

    @Test
    public void testDelete() {
        List<ModelEntityImp> selected = new ArrayList<>();
        selected.add(entityImp);
        selected.add(new ModelEntityImp());
        entitiesMB.setSelectedEntities(selected);

        List<ModelEntityImp> entityImps = new ArrayList<>();
        Mockito.when(service.findAll()).thenReturn(entityImps);

        Mockito.doNothing().when(service).remove(Mockito.any(ModelEntityImp.class));

        entitiesMB.delete();

        Assert.assertEquals(entityImps, entitiesMB.getEntities());
        Mockito.verify(service, Mockito.times(2)).remove(Mockito.any(ModelEntityImp.class));
    }

    @Test
    public void testLoad() {
        List<ModelEntityImp> entityImps = new ArrayList<>();
        entityImps.add(entityImp);

        Mockito.when(service.findAll()).thenReturn(entityImps);

        entitiesMB.load();

        Assert.assertEquals(entityImps, entitiesMB.getEntities());
    }

    @Test
    public void testLoadError() {
        Mockito.when(service.findAll()).thenThrow(new BusinessException());

        entitiesMB.load();

        Assert.assertTrue(entitiesMB.getEntities().isEmpty());
    }

    @Test
    public void testGenerateRemoveMessageDetails() {
        Assert.assertNotNull(entitiesMB.generateRemoveMessageDetails());
    }

    @Test
    public void testDisableDelete() {
        ToggleSelectEvent event = Mockito.mock(ToggleSelectEvent.class);
        Mockito.when(event.isSelected()).thenReturn(true);

        entitiesMB.toggleSelect(event);

        Assert.assertTrue(entitiesMB.disableDelete());
    }

    @Test
    public void testFormView() {
        Assert.assertEquals(EntitiesImpMB.FORM, entitiesMB.formView());
    }
}