package com.akybenko.solutions.company.gateway.service;

import com.akybenko.solutions.company.gateway.model.ui.CreateUserRequestModel;
import com.akybenko.solutions.company.gateway.model.ui.CreateUserResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    CreateUserResponseModel createUser(CreateUserRequestModel model);
}
