package edu.epitech.timemanager.configurations.filters;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epitech.timemanager.domains.mappers.UserMappers;
import edu.epitech.timemanager.domains.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserMappers userMappers = Mappers.getMapper(UserMappers.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String identifier = request.getParameter("identifier");
        String password = request.getParameter("password");
        log.info("Attempting authentication with identifier: " + identifier + " and password: " + password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(identifier, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        log.info("Authentication successful for user: " + user.getUsername());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String token = com.auth0.jwt.JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .withClaim("id", user.getId())
            .sign(algorithm);
        String refreshToken = com.auth0.jwt.JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .withClaim("id", user.getId())
            .sign(algorithm);
        Map<String, Object> tokens = Map.of("access_token", token, "refresh_token", refreshToken, "user", userMappers.userToUserDto(user));
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
