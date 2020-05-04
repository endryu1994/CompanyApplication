package com.akybenko.solutions.company.gateway.controller;

import com.akybenko.solutions.company.gateway.model.jwt.JwtRequest;
import com.akybenko.solutions.company.gateway.model.jwt.JwtResponse;
import com.akybenko.solutions.company.gateway.model.ui.CreateUserRequestModel;
import com.akybenko.solutions.company.gateway.model.ui.CreateUserResponseModel;
import com.akybenko.solutions.company.gateway.security.JwtTokenUtil;
import com.akybenko.solutions.company.gateway.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "User Controller")
@RequestMapping(value = "/api/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Add a new user", response = CreateUserResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "User already exists")})
    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel user) {
        CreateUserResponseModel response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Get auth token", response = JwtResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token received"),
            @ApiResponse(code = 400, message = "Invalid credentials or request")})
    @PostMapping(value = "/auth")
    public ResponseEntity<JwtResponse> getJwtToken(@Valid @RequestBody JwtRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse response = new JwtResponse(token);
        return ResponseEntity.ok(response);
    }
}
