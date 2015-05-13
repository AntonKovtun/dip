package com.dip.backend.service;

import java.util.List;

import com.dip.backend.dao.IUserDao;
import com.dip.backend.domain.UserEntity;
import com.dip.common.dto.UserDto;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import com.dip.frontend.web.model.UserWebModel;

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
