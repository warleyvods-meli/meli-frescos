//package com.mercadolibre.dambetan01.controller;
//
//import com.mercadolibre.dambetan01.dtos.request.UserRequestDTO;
//import com.mercadolibre.dambetan01.dtos.response.AccountResponseDTO;
//import com.mercadolibre.dambetan01.dtos.response.UserResponseDTO;
//import com.mercadolibre.dambetan01.service.ISessionService;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import javassist.NotFoundException;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping(path = "/api/v1")
//@RestController
//public class SessionController {
//    private final ISessionService service;
//
//    public SessionController(ISessionService sessionService) {
//        this.service = sessionService;
//    }
//
//    @PostMapping("internal/sign-in")
//    public UserResponseDTO internalLogin(@Validated @RequestBody UserRequestDTO request) throws NotFoundException {
//        return service.internalLogin(request.getEmail(), request.getPassword());
//    }
//
//    @PostMapping("sign-in")
//    public AccountResponseDTO login(@Validated @RequestBody UserRequestDTO request) throws NotFoundException {
//        return service.login(request.getEmail(), request.getPassword());
//    }
//
//}
