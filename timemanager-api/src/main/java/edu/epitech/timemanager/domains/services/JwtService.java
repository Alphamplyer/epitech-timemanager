package edu.epitech.timemanager.domains.services;

import edu.epitech.timemanager.configurations.security.RoleGrantedPermissions;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String secret_key;

    private static final long TOKEN_EXPIRATION_TIME = 24 * 60 * 60;
    private static final long REMEMBER_ME_TOKEN_EXPIRATION_TIME = 30 * 24 * 60 * 60;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();

    public String generateToken(User user, boolean generateRememberMeToken) {
        long expirationTime = generateRememberMeToken ? REMEMBER_ME_TOKEN_EXPIRATION_TIME : TOKEN_EXPIRATION_TIME;
        long currentTime = System.currentTimeMillis();

        return Jwts.builder()
            .setExpiration(new Date(currentTime + expirationTime * 1000L))
            .setIssuedAt(new Date(currentTime))
            .setSubject(user.getEmail())
            .claim("role", user.getRole().toString())
            .signWith(SignatureAlgorithm.HS512, secret_key)
            .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            logger.debug("Invalid Token");
        } catch (ExpiredJwtException ex) {
            logger.debug("Expired token");
        }
        return false;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }

    public String getEmail(Claims tokenClaims) {
        return tokenClaims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRoles(Claims tokenClaims) {
        List<SimpleGrantedAuthority> roles = null;
        Role role = Role.valueOf(tokenClaims.get("role", String.class));
        return RoleGrantedPermissions.getPermissions(role);
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX_LENGTH);
        }
        return null;
    }
}
