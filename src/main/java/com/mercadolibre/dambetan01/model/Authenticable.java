package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.dtos.response.UserResponseDTO;

public interface Authenticable {

     UserResponseDTO autenticate(String email, String password);
    
}
