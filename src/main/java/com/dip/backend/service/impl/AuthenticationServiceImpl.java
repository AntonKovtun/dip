package com.dip.backend.service.impl;

import com.dip.backend.config.IBackendProperties;
import com.dip.backend.dao.IAuthStateDao;
import com.dip.backend.dao.IUserDao;
import com.dip.backend.domain.AuthStateEntity;
import com.dip.backend.domain.UserEntity;
import com.dip.backend.service.IAuthenticationService;
import com.dip.backend.service.module.IAuthTokenModule;
import com.dip.common.constant.ErrorCodes;
import com.dip.common.constant.ResponseStatus;
import com.dip.common.constant.Status;
import com.dip.common.dto.AuthToken;
import com.dip.common.dto.AuthenticationResponse;
import com.dip.common.exception.BasicServiceException;
import com.dip.common.searchbean.UserSearchBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("authenticate")
@Transactional
public class AuthenticationServiceImpl implements IAuthenticationService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBackendProperties properties;
    @Autowired
    private IAuthStateDao authStateDao;
    @Autowired
    private IAuthTokenModule authTokenModule;
    @Autowired
    private IUserDao userDao;

    @Override
    public AuthenticationResponse login(String principal, String password) {
        final AuthenticationResponse response = new AuthenticationResponse(ResponseStatus.FAILURE);
        try{
            if(StringUtils.isBlank(principal)){
                throw new BasicServiceException(ErrorCodes.AUTH_LOGIN_IS_EMPTY, "Empty Login is provided");
            }
            if(StringUtils.isBlank(password)){
                throw new BasicServiceException(ErrorCodes.AUTH_PASSWORD_IS_EMPTY, "Empty Password is provided");
            }
            UserSearchBean ub = new UserSearchBean();
            ub.setLogin(principal);

            List<UserEntity> userEntityList = new ArrayList<>();

            userEntityList.addAll(userDao.getByExample(ub));

            if (CollectionUtils.isEmpty(userEntityList) ) {
                throw new BasicServiceException(ErrorCodes.AUTH_USER_NOT_FOUND, "User not found for given principal");
            }

            UserEntity user = null;

            int keyPassword = 0;
            for (UserEntity baseUserEntity : userEntityList) {
                user = baseUserEntity;
                if (user.getPassword() != null){
                    if (user.getPassword().equals(password)){
                        keyPassword = 1;
                        break;
                    }
                }
            }
            if (keyPassword == 0){
                throw new BasicServiceException(ErrorCodes.AUTH_INVALID_LOGIN_AND_PWD_PAIR, "User not found for given principal and password");
            }

            Map tokenParam = new HashMap();
            tokenParam.put("PRINCIPAL", principal);
            tokenParam.put("USER_ROLE", user.getUserType());

            response.setUserId(user.getId());
            response.setPrincipal(principal);
            response.setAuthToken(token(user.getId(), tokenParam));

            AuthStateEntity state = new AuthStateEntity(new BigDecimal(1),
                                                        response.getAuthToken().getExpirationTime().getTime(),
                                                        response.getAuthToken().getToken(), response.getUserId());
            authStateDao.saveAuthState(state);
            response.setStatus(ResponseStatus.SUCCESS);


        } catch (BasicServiceException ex){
            log.error(ex.getMessage(), ex);
            response.fail();
        }

        return response;
    }

    @Override
    public void logout(String userId) {
        try{
            AuthStateEntity authSt = authStateDao.findById(userId);
            if (authSt == null) {
                log.error("AuthState not found for userId=" + userId);
                throw new Exception("AuthState not found for userId=" + userId);
            }

            authSt.setAuthState(new BigDecimal(0));
            authSt.setToken("LOGOUT");
            authStateDao.saveAuthState(authSt);
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public AuthenticationResponse renewToken(String principal, String token, String usId) {
        log.debug("RenewToken called.");
        AuthenticationResponse resp = new AuthenticationResponse(ResponseStatus.SUCCESS);

        // validateToken first
        UserSearchBean ub = new UserSearchBean();
        ub.setLogin(principal);
        if (StringUtils.isBlank(usId)) {
            ub.addKey(usId);
        }

        List<UserEntity> userEntityList = new ArrayList<>();

        userEntityList.addAll(userDao.getByExample(ub, 0, 1));

        if (CollectionUtils.isEmpty(userEntityList)) {
            resp.setStatus(ResponseStatus.FAILURE);
            return resp;
        }

        UserEntity user = userEntityList.get(0);

        Map tokenParam = new HashMap();
        tokenParam.put("USER_ID", user.getId());
        tokenParam.put("PRINCIPAL", principal);
        tokenParam.put("USER_ROLE", user.getUserType());

        if (!isUserStatusValid(user.getId())) {
            log.debug("RenewToken: user status failed for userId = " + user.getId());
            resp.setStatus(ResponseStatus.FAILURE);
            return resp;
        }

        AuthStateEntity authSt = authStateDao.findById(user.getId());
        if (authSt != null) {
            if (authSt.getToken() == null
                || "LOGOUT".equalsIgnoreCase(authSt.getToken())) {
                resp.setStatus(ResponseStatus.FAILURE);
                return resp;
            }
        }

        try {
            if (!authTokenModule.isTokenValid( user.getId(), principal, token)) {
                resp.setStatus(ResponseStatus.FAILURE);
                return resp;
            }

            AuthToken authToken = authTokenModule.createToken(tokenParam);
            resp.setAuthToken(authToken);
        } catch (Throwable e) {
            resp.setStatus(ResponseStatus.FAILURE);
            resp.setErrorText(e.getMessage());
        }
        return resp;
    }

    private AuthToken token(String userId, Map tokenParam) throws BasicServiceException {
        log.debug("Generating Security Token");
        tokenParam.put("USER_ID", userId);
        try {
            return authTokenModule.createToken(tokenParam);
        } catch (Exception e) {
            throw new BasicServiceException(ErrorCodes.AUTH_CANNOT_GENERATE_TOKEN, "Cannot generate the auth token");
        }
    }

    private boolean isUserStatusValid(String userId) {
        UserEntity u = userDao.findById(userId);

        Status en = u.getStatus();

        if (en == Status.Inactive || en == Status.Deleted) {
            return false;
        }
        return true;
    }
}
