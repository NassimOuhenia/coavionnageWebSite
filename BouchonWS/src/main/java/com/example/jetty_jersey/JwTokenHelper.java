package com.example.jetty_jersey;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwTokenHelper {
    
    private static JwTokenHelper jwTokenHelper = null;
    
    private static final long EXPIRATION_LIMIT = 1;
    
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private JwTokenHelper() {
    }
    
    synchronized public static JwTokenHelper getInstance() {
       if(jwTokenHelper == null)
            jwTokenHelper = new JwTokenHelper();
       return jwTokenHelper;
    }
    //  1
    public String generatePrivateKey(String username, String password) {
	return Jwts.builder()
		.setId("jesuisunid")
		.setExpiration(getExpirationDate())
		.signWith(key)
		.compact();
    }
    
    // 2
    public void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException  {
       Jwts
                 .parser()
                 .setSigningKey(key)
                 .parseClaimsJws(privateKey);
    }
    // 3
    private Date getExpirationDate() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
    
    public String getIdFromToken(String token) {
	return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getId();
    }
    
    public boolean isTokenValid(String token) {
	try {
	    Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	    return true;
	}
	catch(Exception e) {
	    return false;
	}
    }
 }