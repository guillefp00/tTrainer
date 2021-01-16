package com.upc.ittrainer.view;

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

import javax.faces.context.Flash;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Faces.class, MessagesBean.class})
public class FormMBTest {

    private FormImpMB form;
    private ServiceImp service;
    private ModelEntityImp entityImp;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Faces.class);
        PowerMockito.mockStatic(MessagesBean.class);
        service = Mockito.mock(ServiceImp.class);
        form = new FormImpMB(service);
        entityImp = ModelEntityImp.create();
    }

    @Test
    public void testInit() {
        PowerMockito.when(Faces.isAjaxRequest()).thenReturn(false);
        Mockito.when(service.newEntityInstance()).thenReturn(new ModelEntityImp());

        form.init();

        Assert.assertTrue(form.getEntity().isNew());
        Assert.assertNull(form.getId());
    }

    @Test
    public void testInitUpdate() {
        Mockito.when(service.findById(ModelEntityImp.ID_TEST)).thenReturn(entityImp);

        form.setId(ModelEntityImp.ID_TEST);

        form.init();

        Assert.assertEquals(entityImp, form.getEntity());
    }

    @Test
    public void testRemove() throws IOException {
        Flash flash = Mockito.mock(Flash.class);
        PowerMockito.when(Faces.getFlash()).thenReturn(flash);
        form.setId(ModelEntityImp.ID_TEST);
        form.setEntity(entityImp);

        Mockito.doNothing().when(service).remove(entityImp);

        form.remove();

        Mockito.verify(service, Mockito.times(1)).remove(entityImp);
    }

    @Test
    public void testRemoveNothing() throws IOException {
        form.remove();

        Mockito.verify(service, Mockito.times(0)).remove(entityImp);
    }

    @Test
    public void testListView() {
        Assert.assertEquals(FormImpMB.LIST_VIEW, form.listView());
    }

    @Test
    public void testSave() {
        ModelEntityImp newEntity = new ModelEntityImp();
        newEntity.setField(ModelEntityImp.FIELD_TEST);
        form.setEntity(newEntity);
        Mockito.when(service.insert(newEntity)).thenReturn(entityImp);

        form.save();

        Assert.assertEquals(entityImp, form.getEntity());
        Assert.assertEquals(ModelEntityImp.ID_TEST, form.getId());
    }

    @Test
    public void testSaveUpdate() {
        entityImp.setField("test");
        form.setEntity(entityImp);
        Mockito.when(service.update(entityImp)).thenReturn(entityImp);

        form.save();

        Assert.assertEquals(entityImp, form.getEntity());
    }

    @Test
    public void testIsNew() {
        Assert.assertTrue(form.isNew());
        form.setId(ModelEntityImp.ID_TEST);
        form.setEntity(new ModelEntityImp());
        Assert.assertTrue(form.isNew());

        form.setEntity(entityImp);
        Assert.assertFalse(form.isNew());
    }

    @Test
    public void testClear() {
        form.setEntity(entityImp);
        form.clear();
        Assert.assertNull(form.getId());
    }
}