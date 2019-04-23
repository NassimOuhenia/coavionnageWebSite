package com.example.jetty_jersey;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwTokenHelper {
    
    private static JwTokenHelper jwTokenHelper = null;
    
    private static final long EXPIRATION_LIMIT = 30;
    
    
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private JwTokenHelper() {
    }
    
    synchronized public static JwTokenHelper getInstance() {
       if(jwTokenHelper == null)
            jwTokenHelper = new JwTokenHelper();
       return jwTokenHelper;
    }
    
    public String generatePrivateKey(String id, String user) {
	    return Jwts.builder()
		    .setId(id)
		    .setExpiration(getExpirationDate())
		    .claim("user", user)
		    .signWith(key)
		    .compact();
    }
    
    private Date getExpirationDate() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
    
    
    
    public String getIdFromToken(String token) {
	return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getId();
    }
    
    public String getUserType (String token) {
	return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("user").toString();
    }
    
    
    
    public boolean isTokenInvalid(String token) {
	try {
	    Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	    return false;
	}
	catch(Exception e) {
	    return true;
	}
    }
 }