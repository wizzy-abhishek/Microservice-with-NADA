package com.fakeBankDetails.fakeBank.service.interfaces;

import com.fakeBankDetails.fakeBank.entity.UserEntity;

public interface JWTServiceInterface {
    String generateAccessToken(UserEntity userEntity);
    String generateRefreshToken(UserEntity user);
    String getUserFromToken(String token);
}
