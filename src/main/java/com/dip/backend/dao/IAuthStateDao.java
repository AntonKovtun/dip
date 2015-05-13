package com.sulin.backend.dao;

import com.sulin.backend.domain.AuthStateEntity;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
public interface IAuthStateDao {
    public AuthStateEntity findById(String id);
    public void saveAuthState(final AuthStateEntity authState);
    public void deleteByUser(String userId);
}
