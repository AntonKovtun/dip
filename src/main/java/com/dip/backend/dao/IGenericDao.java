package com.dip.backend.dao;

import com.dip.backend.domain.BaseEntity;
import com.dip.common.searchbean.AbstractSearchBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface IGenericDao<Entity extends BaseEntity, PrimaryKey extends Serializable, Query extends AbstractSearchBean> {
    Entity findById(PrimaryKey id);

    Entity findInitializedObjectById(PrimaryKey id);

    Entity findById(PrimaryKey id, String... fetchFields);

    List<Entity> findByIds(Collection<PrimaryKey> idCollection);

    List<Entity> findByIds(Collection<PrimaryKey> idCollection, final int from, final int size);

    List<Entity> findAll();

    Long countAll();

    void update(Entity t);

    Entity merge(Entity t);

    void refresh(Entity t);

    Entity save(Entity t);

    Entity add(Entity t);

    void persist(Entity t);

    void delete(Entity t);

    void save(Collection<Entity> entities);

    void deleteAll() throws Exception;

    void attachDirty(Entity t);

    void attachClean(Entity t);

    void evict(Entity t);

    List<Entity> getByExample(Entity t, int startAt, int size);

    List<Entity> getByExample(Entity t);

    List<Entity> getByExample(Query searchBean);

    List<Entity> getByExample(Query searchBean, int from, int size);

    List<String> getIDsByExample(Query searchBean, int from, int size);

    int count(Query searchBean);

    int count(Entity t);

    void flush();

}
