package com.user.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;


@Component
public class JwtTokenUtil {
	private static final long EXPIRE_DURATION = 600000l;

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	public String generateAccessToken(String name,String role,Integer userId) {
		String token = JWT.create().withClaim("name",name).withClaim("role",role).withClaim("userId",userId)
				   .withExpiresAt(new Date(System.currentTimeMillis() + 90000l))
				   .sign(Algorithm.HMAC256(SECRET_KEY));
		return token;
	}
	
		  public boolean isValidBearerToken(String token) throws IOException{
			    boolean isValid = false;
			    try {
			      DecodedJWT jwt = JWT.decode(token);
			      long jwtExpiresAt = jwt.getExpiresAt().getTime()/1000;
			      long difference = jwtExpiresAt - (System.currentTimeMillis()/1000);
			      if(jwt!= null  && difference >= 30){ 
			        isValid = true;
			      }
			    } catch (JWTDecodeException exception){
			      throw new IOException(exception.getMessage());
			    }
			    return isValid;
			  }
			
	}
	

