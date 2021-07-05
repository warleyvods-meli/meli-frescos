package com.mercadolibre.dambetan01.service;


import com.mercadolibre.dambetan01.dtos.response.UserResponseDTO;
import javassist.NotFoundException;

public interface ISessionService {

    UserResponseDTO login(String username, String password) throws NotFoundException;

    UserResponseDTO internalLogin(String username, String password) throws NotFoundException;
}
