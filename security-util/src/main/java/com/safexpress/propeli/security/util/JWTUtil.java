package com.safexpress.propeli.security.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.safexpress.propeli.security.domain.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JWTUtil {
	private static final String CUSTOM_CLAIM_KEY = "token";
	private static final String ISSUER = "safexpress";
	private static final String API_KEY = "safexpress-key";

	private JWTUtil() {
	}

	public static String createJWT(String subject, Token customClaims, long keepAliveTime) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		long nowMillis = System.currentTimeMillis();

		JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date(System.currentTimeMillis())).setSubject(subject).setIssuer(ISSUER)
				.claim(CUSTOM_CLAIM_KEY, customClaims).signWith(signatureAlgorithm, signingKey);

		if (keepAliveTime >= 0) {
			long expMillis = nowMillis + keepAliveTime;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public static Claims parseJWT(String jwt) {

		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY)).parseClaimsJws(jwt).getBody();

	}

	public static void main(String[] args) {
		Token token = new Token();
		token.setUserId(2121);
		Map<String, Set<String>> permissions = new HashMap<String, Set<String>>();
		Set<String> per = new HashSet<String>();
		per.add("READ");
		permissions.put("contact", per);
		token.setPermissions(permissions);
		String jwtToken = createJWT("arun singh", token, 2111212);
		Claims claims = parseJWT(jwtToken);
		System.out.println("JWT Token: " + jwtToken);
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		System.out.println("Token: " + claims.get(CUSTOM_CLAIM_KEY));
	}
}
