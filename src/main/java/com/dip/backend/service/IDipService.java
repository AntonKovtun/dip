package com.dip.backend.service;

import com.dip.backend.dao.IUserDao;
import com.dip.backend.domain.UserEntity;
import com.dip.common.dto.DipDto;
import com.dip.common.dto.UserDto;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import com.dip.frontend.web.model.DipModel;
import com.dip.frontend.web.model.UserWebModel;

import java.util.List;

public interface IDipService {
    public DipDto mainMethod(DipModel dipModel) throws Exception;

}
