package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.UserResponseDTO;
import com.mercadolibre.dambetan01.exceptions.error.UnauthorizedException;
import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.model.Seller;
import com.mercadolibre.dambetan01.repository.AgentRepository;
import com.mercadolibre.dambetan01.repository.BuyerRepository;
import com.mercadolibre.dambetan01.repository.SellerRepository;
import com.mercadolibre.dambetan01.service.ISessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements ISessionService {

    private final AgentRepository agentRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    public SessionServiceImpl(AgentRepository agentRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.agentRepository = agentRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public UserResponseDTO login(String email, String password) {

        if (sellerRepository.findByEmailAndPassword(email, password).isPresent()){
            return this.sellerLogin(email,password);
        }else if (buyerRepository.findByEmailAndPassword(email, password).isPresent()){
            return this.buyerLogin(email, password);
        }
        throw new UnauthorizedException("email or password is invalid");
    }

    private UserResponseDTO buyerLogin(String email, String password) {
        Buyer buyer = buyerRepository.findByEmailAndPassword(email, password).get();
        String token = getJWTToken(email);
        UserResponseDTO user = new UserResponseDTO();
        user.setUsername(email);
        user.setToken(token);
        user.setUserType(buyer.getPersonalData().getUserType().toString());
        return user;
    }

    private UserResponseDTO sellerLogin(String email, String password) {
        Seller seller = sellerRepository.findByEmailAndPassword(email, password).get();
        String token = getJWTToken(email);
        UserResponseDTO user = new UserResponseDTO();
        user.setUsername(email);
        user.setToken(token);
        user.setUserType(seller.getPersonalData().getUserType().toString());
        return user;
    }

    @Override
    public UserResponseDTO internalLogin(String email, String password) throws NotFoundException {
        Agent agent = agentRepository.findByEmailAndPassword(email, password);
        if (agent != null) {
            String token = getJWTToken(email);
            UserResponseDTO user = new UserResponseDTO();
            user.setUsername(email);
            user.setToken(token);
            user.setUserType(agent.getPersonalData().getUserType().toString());
            return user;
        } else {
            throw new UnauthorizedException("email or password is invalid");
        }
    }

    /**
     * Genera un token para un usuario específico, válido por 1 min'
     * @param username
     * @return
     */
    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    /**
     * Decodifica un token para poder obtener los componentes que contiene el mismo
     * @param token
     * @return
     */
    private static Claims decodeJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey("mySecretKey".getBytes())
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * Permite obtener el username según el token indicado
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = decodeJWT(token);
        return claims.get("sub", String.class);
    }

}
