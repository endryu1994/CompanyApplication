package com.akybenko.solutions.company.gateway.service.impl;

import com.akybenko.solutions.company.gateway.model.dto.UserDTO;
import com.akybenko.solutions.company.gateway.model.entity.UserEntity;
import com.akybenko.solutions.company.gateway.model.ui.CreateUserRequestModel;
import com.akybenko.solutions.company.gateway.model.ui.CreateUserResponseModel;
import com.akybenko.solutions.company.gateway.repository.UserRepository;
import com.akybenko.solutions.company.gateway.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByEmail(username).orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User not found with email: %s", username)));
        return new User(entity.getEmail(), entity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public CreateUserResponseModel createUser(CreateUserRequestModel model) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDto = mapper.map(model, UserDTO.class);
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return mapper.map(userDto, CreateUserResponseModel.class);
    }
}
