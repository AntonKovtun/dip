package com.sulin.backend.dao.impl;

import com.sulin.backend.dao.AbstractGenericDao;
import com.sulin.backend.dao.IUserDao;
import com.sulin.backend.domain.UserEntity;
import com.sulin.common.constant.Status;
import com.sulin.common.searchbean.UserSearchBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Created by: andrew
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractGenericDao<UserEntity, String, UserSearchBean> implements IUserDao {

    @Override
    protected Criteria getExampleCriteria(final UserSearchBean searchBean) {

        final Criteria criteria = getCriteria();
        if(searchBean.hasMultipleKeys()){
            criteria.add(Restrictions.in(getPKfieldName(), searchBean.getKeys()));
        } else if (StringUtils.isNotBlank(searchBean.getKey())) {
            criteria.add(Restrictions.eq(getPKfieldName(), searchBean.getKey()));
        } else {
            if (StringUtils.isNotEmpty(searchBean.getLogin())) {
                criteria.add(Restrictions.eq("login", searchBean.getLogin()));
            }

            if (searchBean.getUserType() != null) {
                criteria.add(Restrictions.eq("userType", searchBean.getUserType()));
            }

            criteria.add(Restrictions.eq("status", Status.Active));

            if (StringUtils.isNotEmpty(searchBean.getName())) {
                criteria.add(Restrictions.like("name", searchBean.getName(), MatchMode.ANYWHERE));
            }

            if (StringUtils.isNotEmpty(searchBean.getEmail())) {
                criteria.add(Restrictions.eq("email", searchBean.getEmail()));
            }
        }
        criteria.addOrder(Order.asc("name"));
        return criteria;
    }
}
