package com.sulin.backend.dao;

import com.sulin.backend.domain.BaseEntity;
import com.sulin.common.searchbean.AbstractSearchBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: alexander Date: 10/27/13 Time: 9:03 PM To
 * change this template use File | Settings | File Templates.
 */
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
