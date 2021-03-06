//package com.mercadolibre.dambetan01.service.impl;
//
//import com.mercadolibre.dambetan01.dtos.response.AccountResponseDTO;
//import com.mercadolibre.dambetan01.dtos.response.UserResponseDTO;
//import com.mercadolibre.dambetan01.exceptions.error.UnauthorizedException;
//import com.mercadolibre.dambetan01.model.Account;
//import com.mercadolibre.dambetan01.model.Agent;
//import com.mercadolibre.dambetan01.model.Buyer;
//import com.mercadolibre.dambetan01.model.Seller;
//import com.mercadolibre.dambetan01.repository.AccountRepository;
//import com.mercadolibre.dambetan01.repository.AgentRepository;
//import com.mercadolibre.dambetan01.repository.BuyerRepository;
//import com.mercadolibre.dambetan01.repository.SellerRepository;
//import com.mercadolibre.dambetan01.service.ISessionService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import javassist.NotFoundException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SessionServiceImpl implements ISessionService {
//
//    private final AccountRepository accountRepository;
//
//    public SessionServiceImpl(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    /**
//     * Realiza la validación del usuario y contraseña ingresado.
//     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
//     *
//     * @param username
//     * @param password
//     * @return
//     * @throws NotFoundException
//     */
//    @Override
//    public AccountResponseDTO login(String username, String password) throws NotFoundException {
//        //Voy a la base de datos y reviso que el usuario y contraseña existan.
//        Account account = accountRepository.findByUsernameAndPassword(username, password);
//
//        if (account != null) {
//            String token = getJWTToken(username);
//            AccountResponseDTO user = new AccountResponseDTO();
//            user.setUsername(username);
//            user.setToken(token);
//            return user;
//        } else {
//            throw new NotFoundException("404");
//        }
//
//    }
//
//    @Override
//    public UserResponseDTO internalLogin(String username, String password) throws NotFoundException {
//        return null;
//    }
//
//    /**
//     * Genera un token para un usuario específico, válido por 10'
//     * @param username
//     * @return
//     */
//    private String getJWTToken(String username) {
//        String secretKey = "mySecretKey";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
//        return "Bearer " + token;
//    }
//
//    /**
//     * Decodifica un token para poder obtener los componentes que contiene el mismo
//     * @param token
//     * @return
//     */
//    private static Claims decodeJWT(String token) {
//        Claims claims = Jwts.parser().setSigningKey("mySecretKey".getBytes())
//                .parseClaimsJws(token).getBody();
//        return claims;
//    }
//
//    /**
//     * Permite obtener el username según el token indicado
//     * @param token
//     * @return
//     */
//    public static String getUsername(String token) {
//        Claims claims = decodeJWT(token);
//        return claims.get("sub", String.class);
//    }
//
//}
