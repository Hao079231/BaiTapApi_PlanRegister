package vn.itz.plansync.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Value("${jwt.signerKey}")
  @NonFinal
  protected String SIGN_KEY;

  public boolean isValid(String token){
    return !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> resolvers){
    Claims claims = extractAllClaims(token);
    return resolvers.apply(claims);
  }

  public Claims extractAllClaims(String token){
    return Jwts.parserBuilder()
        .setSigningKey(getSigninKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }


  private SecretKey getSigninKey(){
    byte[] keyBytes = Decoders.BASE64URL.decode(SIGN_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
