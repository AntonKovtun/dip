package com.dip.backend.service.impl;

import com.dip.backend.config.IBackendProperties;
import com.dip.backend.dao.IUserDao;
import com.dip.backend.domain.UserEntity;
import com.dip.backend.service.*;
import com.dip.common.constant.ErrorCodes;
import com.dip.common.constant.Status;
import com.dip.common.dto.UserDto;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import com.dip.frontend.web.model.UserWebModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBackendProperties backendProperties;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> search(UserSearchBean searchBean) throws BasicServiceException {
        List<UserDto> result = new ArrayList<UserDto>();

        List<UserEntity> entityList = userDao.getByExample(searchBean);

        if (CollectionUtils.isEmpty(entityList))
            throw new BasicServiceException(ErrorCodes.RECORD_NOT_FOUND);

        for (UserEntity ue : userDao.getByExample(searchBean)) {
            result.add(toDto(ue));
        }

        return result;
    }

    @Override
    public UserDto register(UserWebModel dataModel, String loginUserId) throws Exception {

        UserDto userDto = new UserDto();

        if ("-1".equals(dataModel.getId())) {
            userDto.setId(null);
        } else {
            userDto.setId(dataModel.getId());
        }
        userDto.setName(dataModel.getName());
        userDto.setDescription(dataModel.getDescription());
        userDto.setEmail(dataModel.getEmail());
        userDto.setLogin(dataModel.getEmail());
        userDto.setPassword(dataModel.getPassword());
        userDto.setPhone(dataModel.getPhone());
        userDto.setUserType(dataModel.getUserType());
        userDto.setStatus(dataModel.getStatus());
        userDto.setUserType(dataModel.getUserType());

        UserSearchBean usb = new UserSearchBean();
        UserDto uDto = new UserDto();
        usb.addKey(dataModel.getId());

        try {
            uDto = search(usb).get(0);
            userDto.setCreateTime(uDto.getCreateTime());
            userDto.setLastUpdateTime(Calendar.getInstance().getTime());
        } catch (BasicServiceException e) {
            userDto.setCreateTime(Calendar.getInstance().getTime());
        }

        UserSearchBean usb1 = new UserSearchBean();
        UserDto userCreateDto = new UserDto();
        usb1.addKey(dataModel.getId());
        try {
            userCreateDto = search(usb1).get(0);
            userDto.setCreateByUserId(userCreateDto.getCreateByUserId());
            userDto.setUpdateByUserId(loginUserId);
        } catch (BasicServiceException e) {
            userDto.setCreateByUserId(loginUserId);
        }

        UserEntity userEntity = userDao.merge(toEntity(userDto));

        return toDto(userEntity);
    }


    // @Override
    // public UserDto save(UserDto userDto) throws BasicServiceException {
    // UserEntity e = userDao.save(toEntity(userDto));
    // return toDto(e);
    // }

    @Override
    public void delete(String pk) throws BasicServiceException {
//        userDao.delete(userDao.findById(pk));
        UserEntity userEntity = userDao.findById(pk);
        userEntity.setStatus(Status.Deleted);
        userDao.merge(userEntity);
    }

    public UserEntity toEntity(UserDto userDto) throws BasicServiceException {
        UserEntity entity = new UserEntity();

        entity.setId(userDto.getId());
        entity.setName(userDto.getName());
        entity.setDescription(userDto.getDescription());
        entity.setPassword(userDto.getPassword());
        entity.setEmail(userDto.getEmail());
        entity.setLogin(userDto.getEmail());
        entity.setPhone(userDto.getPhone());
        entity.setUserType(userDto.getUserType());
        entity.setStatus(userDto.getStatus());
        entity.setCreateByUserId(userDto.getCreateByUserId());
        entity.setCreateTime(userDto.getCreateTime());
        entity.setLastUpdateTime(userDto.getLastUpdateTime());
        entity.setUpdateByUserId(userDto.getUpdateByUserId());

        return entity;
    }

    public UserDto toDto(UserEntity entity) {
        UserDto userDto = new UserDto();

        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setDescription(entity.getDescription());
        userDto.setPassword(entity.getPassword());
        userDto.setEmail(entity.getEmail());
        userDto.setLogin(entity.getEmail());
        userDto.setPhone(entity.getPhone());
        userDto.setUserType(entity.getUserType());
        userDto.setStatus(entity.getStatus());
        userDto.setCreateByUserId(entity.getCreateByUserId());
        userDto.setCreateTime(entity.getCreateTime());
        userDto.setLastUpdateTime(entity.getLastUpdateTime());
        userDto.setUpdateByUserId(entity.getUpdateByUserId());

        return userDto;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public void setUserDao(IUserDao userDao) {
    }

}
