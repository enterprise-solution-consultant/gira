package cybersoft.javabackend.java18.gira.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    private final String secretKey = """
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            """;
    private final String PREFIX = "Bearer ";
    private final Key SECRET_KEY =  Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    public String generateJwt(String username){
        Date currentDate = new Date();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + 86400000))
                .signWith(
                        SECRET_KEY,
                        SignatureAlgorithm.HS512
                )
                .compact();
    }

    public boolean validateJwt(String token) {
        log.info("Token: " + token);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e1) {
            log.error("JWT Token is not supported: ", e1);
        } catch (MalformedJwtException e2) {
            log.error("Invalid Token: ", e2);
        } catch (SignatureException e3) {
            log.error("Invalid signature: ", e3);
        } catch (ExpiredJwtException e4) {
            log.error("JWT is expired: ", e4);
        } catch (IllegalArgumentException e5) {
            log.error("JWT Claims is empty: ", e5);
        }

        return false;
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        if (jwt == null)
            return null;

        return jwt.substring(PREFIX.length(), jwt.length());
    }

    public String getUsername(String token) {
        if (!validateJwt(token))
            return null;

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
