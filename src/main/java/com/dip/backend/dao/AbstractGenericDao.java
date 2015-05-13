package com.dip.backend.dao;

import static org.hibernate.criterion.Projections.rowCount;
import static org.hibernate.criterion.Restrictions.eq;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.dip.backend.domain.BaseEntity;
import com.dip.common.constant.OrderConstants;
import com.dip.common.searchbean.AbstractSearchBean;

public abstract class AbstractGenericDao<Entity extends BaseEntity, PrimaryKey extends Serializable, Query extends AbstractSearchBean>
        implements IGenericDao<Entity, PrimaryKey, Query> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final Class<Entity> domainClass;
    private SessionFactory sessionFactory;

    @Autowired
    public void setTemplate(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<Entity> getDomainClass() {
        return domainClass;
    }

    public AbstractGenericDao() {
        Type t = getClass().getGenericSuperclass();
        Type arg;
        if (t instanceof ParameterizedType) {
            arg = ((ParameterizedType) t).getActualTypeArguments()[0];
        } else if (t instanceof Class) {
            arg = ((ParameterizedType) ((Class) t).getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            throw new RuntimeException(String.format("Can not handle type construction for '%s'!", getClass()));
        }

        if (arg instanceof Class) {
            this.domainClass = (Class<Entity>) arg;
        } else if (arg instanceof ParameterizedType) {
            this.domainClass = (Class<Entity>) ((ParameterizedType) arg).getRawType();
        } else {
            throw new RuntimeException(String.format("Problem determining generic class for '%s'!", getClass()));
        }
    }

    protected Criteria getExampleCriteria(Entity t) {
        return getCriteria().add(Example.create(t));
    }

    protected Criteria getExampleCriteria(final Query searchBean) {
        throw new UnsupportedOperationException("Method must be overridden");
    }

    @Override
    public int count(final Query searchBean) {
        return ((Number) getExampleCriteria(searchBean).setProjection(rowCount()).uniqueResult()).intValue();
    }

    public void flush() {
        getSession().flush();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Entity> getByExample(Entity t, int startAt, int size) {
        final Criteria criteria = getExampleCriteria(t);
        if (startAt > -1) {
            criteria.setFirstResult(startAt);
        }

        if (size > -1) {
            criteria.setMaxResults(size);
        }

        return (List<Entity>) criteria.list();
    }

    @Override
    public List<String> getIDsByExample(Query searchBean, int from, int size) {
        final Criteria criteria = getExampleCriteria(searchBean);
        if (from > -1) {
            criteria.setFirstResult(from);
        }

        if (size > -1) {
            criteria.setMaxResults(size);
        }

        criteria.setProjection(Projections.id());
        return (List<String>) criteria.list();
    }

    @Override
    public List<Entity> getByExample(final Query searchBean) {
        return getByExample(searchBean, -1, -1);
    }

    @Override
    public List<Entity> getByExample(final Query searchBean, int from, int size) {
        final Criteria criteria = getExampleCriteria(searchBean);
        if (from > -1) {
            criteria.setFirstResult(from);
        }

        if (size > -1) {
            criteria.setMaxResults(size);
        }

        if (StringUtils.isNotBlank(searchBean.getSortBy())) {
            criteria.addOrder(searchBean.getOrderBy().equals(OrderConstants.DESC) ? Order.desc(searchBean.getSortBy())
                    : Order.asc(searchBean.getSortBy()));
        }

        return (List<Entity>) criteria.list();
    }

    @Override
    public List<Entity> getByExample(Entity t) {
        return getByExample(t, -1, -1);
    }

    @Override
    public int count(Entity t) {
        return ((Number) getExampleCriteria(t).setProjection(rowCount()).uniqueResult()).intValue();
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(domainClass);
    }

    @SuppressWarnings({ "unchecked" })
    public Entity findById(PrimaryKey id) {
        if (id == null) {
            return null;
        }
        return (Entity) getCriteria().add(eq(getPKfieldName(), id)).uniqueResult(); // this.getSession().get(domainClass,
                                                                                    // id);
    }

    /**
     * So... the reason for this method, is that findById was returning a
     * non-intialized object. WHen setting attributes on Resources, Groups,
     * Roles, etc, this casued a TransientObjectException. The only thing that
     * fixed that, was by calling this method, which calles Session.get.
     * According to the Hibernate docs, 'get' never returns a non-initialized
     * object. Consider removing this in 3.2
     * 
     * @param id
     * @return
     */
    public Entity findInitializedObjectById(PrimaryKey id) {
        final Object o = this.getSession().get(domainClass, id);
        return (o != null) ? (Entity) o : null;
    }

    @SuppressWarnings("unchecked")
    public List<Entity> findByIds(Collection<PrimaryKey> idCollection) {
        return findByIds(idCollection, -1, -1);
    }

    @SuppressWarnings("unchecked")
    public List<Entity> findByIds(Collection<PrimaryKey> idCollection, final int from, final int size) {
        if (CollectionUtils.isEmpty(idCollection)) {
            return (List<Entity>) Collections.EMPTY_LIST;
        }

        final Criteria criteria = getCriteria().add(Restrictions.in(getPKfieldName(), idCollection));

        if (from > -1) {
            criteria.setFirstResult(from);
        }

        if (size > -1) {
            criteria.setMaxResults(size);
        }
        return criteria.list();
    }

    @SuppressWarnings({ "unchecked" })
    public Entity findById(PrimaryKey id, String... fetchFields) {
        if (id == null) {
            return null;
        }
        Criteria criteria = getCriteria().add(eq(getPKfieldName(), id)).setResultTransformer(
                Criteria.DISTINCT_ROOT_ENTITY);
        if (fetchFields != null) {
            for (String field : fetchFields) {
                criteria.setFetchMode(field, FetchMode.JOIN);
            }
        }
        return (Entity) criteria.uniqueResult();
    }

    @SuppressWarnings({ "unchecked" })
    public List<Entity> findAll() {
        return getCriteria().list();
    }

    public Long countAll() {
        return ((Number) getCriteria().setProjection(rowCount()).uniqueResult()).longValue();
    }

    @Transactional
    public Entity save(Entity entity) {
        if (entity != null) {
            getSession().saveOrUpdate(entity);
        }

        return entity;
    }

    @Transactional
    public void refresh(Entity entity) {
        if (entity != null) {
            getSession().refresh(entity);
        }
    }

    @Transactional
    public Entity add(Entity entity) {
        if (entity != null) {
            getSession().persist(entity);
        }
        return entity;
    }

    @Transactional
    public void delete(Entity entity) {
        if (entity != null) {
            getSession().delete(entity);
        }
    }

    @Transactional
    public void save(Collection<Entity> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        Session session = getSession();
        for (Entity entity : entities) {
            session.saveOrUpdate(entity);
        }
    }

    @Override
    @Transactional
    public void update(Entity t) {
        if (t != null) {
            getSession().update(t);
        }
    }

    @Override
    @Transactional
    public Entity merge(Entity t) {
        try {
            if (t != null) {
                return (Entity) getSession().merge(t);
            }
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
        return t;
    }

    @Override
    @Transactional
    public void persist(Entity t) {
        try {
            if (t != null) {
                getSession().persist(t);
            }
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    @Transactional
    public void deleteAll() throws Exception {
        getSession().createQuery("delete from " + this.domainClass.getName()).executeUpdate();
    }

    @Transactional
    public void attachDirty(Entity t) {
        log.debug("attaching dirty instance");
        try {
            this.save(t);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Entity t) {
        log.debug("attaching clean instance");
        try {
            getSession().buildLockRequest(LockOptions.NONE).lock(t);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void evict(Entity t) {
        log.debug("evicting instance");
        try {
            getSession().evict(t);
            log.debug("evict successful");
        } catch (RuntimeException re) {
            log.error("evict failed", re);
            throw re;
        }
    }

    protected String getPKfieldName() {
        return "id";
    }
}
