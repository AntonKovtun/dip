package com.dip.backend.dao;

import com.dip.backend.domain.AuthStateEntity;

public interface IAuthStateDao {
    public AuthStateEntity findById(String id);
    public void saveAuthState(final AuthStateEntity authState);
    public void deleteByUser(String userId);
}
