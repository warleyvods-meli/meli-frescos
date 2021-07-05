package com.mercadolibre.dambetan01.dtos.response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String username;
    private String userType;
    private String token;
}