package com.sulin.backend.dao.impl;

import com.sulin.backend.dao.IAuthStateDao;
import com.sulin.backend.domain.AuthStateEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.criterion.Restrictions.eq;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
@Repository
public class AuthStateDaoImpl implements IAuthStateDao {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Class<AuthStateEntity> domainClass = AuthStateEntity.class;
    private SessionFactory sessionFactory;

    @Autowired
    public void setTemplate(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AuthStateEntity findById(String id) {
        if (id == null) {
            return null;
        }
        return (AuthStateEntity) getCriteria().add(eq("userId", id)).uniqueResult(); // this.getSession().get(domainClass,
        // id);
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    private Criteria getCriteria() {
        return getSession().createCriteria(domainClass);
    }

    @Transactional
    private AuthStateEntity save(AuthStateEntity entity) {
        if (entity != null) {
            getSession().saveOrUpdate(entity);
        }

        return entity;
    }

    @Transactional
    private AuthStateEntity merge(AuthStateEntity t) {
        try {
            if (t != null) {
                return (AuthStateEntity) getSession().merge(t);
            }
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
        return t;
    }

    @Override
    public void saveAuthState(final AuthStateEntity authState) {
        if(findById(authState.getUserId()) == null) {
            save(authState);
        } else {
            merge(authState);
        }
    }


    @Override
    @Transactional
    public void deleteByUser(String userId){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ")
           .append(this.domainClass.getName())
           .append(" where userId=:user");

        getSession().createQuery(sql.toString()).setString("user", userId)
                    .executeUpdate();
    }

}
