package com.dip.backend.dao;

import com.dip.backend.domain.UserEntity;
import com.dip.common.searchbean.UserSearchBean;


public interface IUserDao extends IGenericDao<UserEntity, String, UserSearchBean> {

}
