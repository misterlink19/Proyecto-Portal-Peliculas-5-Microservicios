package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generar token con un único rol
    public String generateToken(String username, String email, String authority) {
        Map<String, Object> claims = Map.of(
                "authority", authority, // Ahora almacenamos el authority correctamente
                "email", email
        );
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extraer claims del token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraer el nombre de usuario (subject)
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Verificar si el token está expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Validar si el token es válido para un usuario en específico
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Extraer autoridad
    public List<GrantedAuthority> extractAuthorities(Claims claims) {
        String authority = claims.get("authority", String.class);
        return List.of(new SimpleGrantedAuthority(authority));
    }
}
