package com.mercadolibre.dambetan01.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {

    public static final String SECRET = "Fish";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGAR = "/usuario/logar";
//    public static final Long EXPIRATION_TIME = 10000L; //<- 10s
    public static final Long EXPIRATION_TIME = 3600000L; //<- 1 Hora

}
