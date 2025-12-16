package com.posify.api.user.service;

import com.posify.api.user.request.UserRequest;
import com.posify.api.user.response.UserResponse;

public interface IUserService {

    UserResponse register(UserRequest request);

}
