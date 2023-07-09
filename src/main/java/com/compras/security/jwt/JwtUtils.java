package com.compras.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private final String secretKey = "vXcpK5ScXhyTyBl216USf0AsQgyKuEpRy1oMDWTMGTU216yKR3EuPK2nkqxjmu7S";
	private final Long timeExpiration = 86400000L;
	
	
	//Generar token de acceso
	public String generateAccesToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Validar el token de acceso
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("Token invalido, error : " + e.getMessage());
			return Boolean.FALSE;
		}
	}
	
	//Obtener el username del token
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	//Obtener el username del Bearer token
	public String getUsernameFromBearerToken(String bearerToken) {
		String token = bearerToken.replace("Bearer ", "");
		return getClaim(token, Claims::getSubject);
	}
	
	
	//Obtener un solo claim
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = extractAllClaims(token);
		return claimsTFunction.apply(claims);
	}
	
	
	//Obtener claims del token (datos)
	public Claims extractAllClaims(String token) {
		return 	Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	//Obtner firma del token
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	

}
