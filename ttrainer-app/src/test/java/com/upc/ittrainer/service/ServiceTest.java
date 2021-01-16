package com.upc.ittrainer.service;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.model.ModelEntityImp;
import com.upc.ittrainer.repository.ModelEntityImpJPARepository;
import com.upc.ittrainer.repository.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceTest {

    private ModelEntityImpJPARepository repository;
    private ServiceImp service;
    private ModelEntityImp entityImp;

    @Before
    public void setUp() {
        repository = Mockito.mock(ModelEntityImpJPARepository.class);
        service = new ServiceImp(repository);
        entityImp = ModelEntityImp.create();
    }

    @Test
    public void testInsert() {
        ModelEntityImp newEntity = new ModelEntityImp();
        newEntity.setField(ModelEntityImp.FIELD_TEST);
        Mockito.when(repository.save(newEntity)).thenReturn(entityImp);

        ModelEntityImp result = service.save(newEntity);

        Assert.assertEquals(entityImp, result);
    }

    @Test(expected = BusinessException.class)
    public void testInsertError() {
        Mockito.when(repository.save(entityImp)).thenThrow(new RepositoryException());

        ModelEntityImp result = service.save(entityImp);
    }

    @Test(expected = BusinessException.class)
    public void testValidateInsert() {
        Mockito.when(repository.existsById(ModelEntityImp.ID_TEST)).thenReturn(true);

        service.validateInsert(entityImp);
    }

    @Test(expected = BusinessException.class)
    public void testValidateInsertError() {
        Mockito.when(repository.existsById(ModelEntityImp.ID_TEST)).thenThrow(new RepositoryException());

        service.validateInsert(entityImp);
    }

    @Test(expected = BusinessException.class)
    public void testValidateInsertEmpty() {
        ModelEntityImp entity = new ModelEntityImp();

        service.validateInsert(entity);
    }

    @Test
    public void testUpdate() {
        ModelEntityImp newEntity = new ModelEntityImp();
        newEntity.setField(ModelEntityImp.FIELD_TEST);
        Mockito.when(repository.save(newEntity)).thenReturn(entityImp);

        ModelEntityImp result = service.update(newEntity);

        Assert.assertEquals(entityImp, result);
    }

    @Test(expected = BusinessException.class)
    public void testUpdateError() {
        Mockito.when(repository.save(entityImp)).thenThrow(new RepositoryException());

        ModelEntityImp result = service.update(entityImp);
    }

    @Test(expected = BusinessException.class)
    public void testRemove() {
        Mockito.doThrow(new RepositoryException()).when(repository).delete(entityImp);

        service.remove(entityImp);
    }

    @Test
    public void testRemoveError() {
        Mockito.doNothing().when(repository).delete(entityImp);

        service.remove(entityImp);

        Mockito.verify(repository, Mockito.times(1)).delete(entityImp);
    }

    @Test
    public void testFindById() {
        Mockito.when(repository.findById(ModelEntityImp.ID_TEST)).thenReturn(Optional.of(entityImp));

        ModelEntityImp result = service.findById(ModelEntityImp.ID_TEST);

        Assert.assertEquals(entityImp, result);
    }

    @Test(expected = BusinessException.class)
    public void testFindByIdEmpty() {

        Mockito.when(repository.findById(ModelEntityImp.ID_TEST)).thenReturn(Optional.empty());

        ModelEntityImp result = service.findById(ModelEntityImp.ID_TEST);
    }

    @Test(expected = BusinessException.class)
    public void testFindByIdError() {

        Mockito.when(repository.findById(ModelEntityImp.ID_TEST)).thenThrow(new RepositoryException());

        ModelEntityImp result = service.findById(ModelEntityImp.ID_TEST);
    }

    @Test
    public void testFindAll() {
        List<ModelEntityImp> entities = new ArrayList<>();
        entities.add(entityImp);
        entities.add(new ModelEntityImp());
        Mockito.when(repository.findAll()).thenReturn(entities);

        List<ModelEntityImp> result = service.findAll();

        Assert.assertEquals(entities, result);
    }

    @Test(expected = BusinessException.class)
    public void testFindAllError() {
        Mockito.when(repository.findAll()).thenThrow(new RepositoryException());

        List<ModelEntityImp> result = service.findAll();
    }

    @Test
    public void testNewEntityInstance() {
        ModelEntityImp entity = service.newEntityInstance();
        Assert.assertTrue(entity.isNew());
    }

    @Test
    public void testGetEntityClass() {
        Assert.assertEquals(ModelEntityImp.class, service.getEntityClass());
    }

    @Test
    public void testGetIdClass() {
        Assert.assertEquals(Integer.class, service.getIdClass());
    }

    @Test
    public void testGetRepository() {
        Assert.assertEquals(repository, service.getRepository());
    }
}