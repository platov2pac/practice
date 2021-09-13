package com.task5.config.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@Log
public class JwtProvider {
    public String generateToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(Date.from(ZonedDateTime.now().plusDays(1).toInstant()))
                .signWith(SignatureAlgorithm.HS512, "GoOdSoFt")
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("GoOdSoFt").parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey("GoOdSoFt").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
