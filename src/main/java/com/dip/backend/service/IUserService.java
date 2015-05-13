package com.sulin.backend.service;

import java.util.List;

import com.sulin.backend.dao.IUserDao;
import com.sulin.backend.domain.UserEntity;
import com.sulin.common.dto.UserDto;
import com.sulin.common.exception.BasicServiceException;
import com.sulin.common.searchbean.UserSearchBean;
import com.sulin.frontend.web.model.UserWebModel;

/**
 * Created by: andrew
 */
public interface IUserService {
    List<UserDto> search(UserSearchBean searchBean) throws BasicServiceException;
//    List<UserDto> searchSome(UserSearchBean searchBean) throws BasicServiceException;

    // UserDto save(UserDto userDto) throws BasicServiceException;

    void delete(String pk) throws BasicServiceException;

    public UserDto toDto(UserEntity entity);

    public UserEntity toEntity(UserDto dto) throws BasicServiceException;

    public IUserDao getUserDao();

    public void setUserDao(IUserDao userDao);

    public UserDto register(UserWebModel dataModel, String loginUserId) throws Exception;

}
