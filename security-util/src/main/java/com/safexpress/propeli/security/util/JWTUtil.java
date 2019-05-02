package com.safexpress.propeli.security.util;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.safexpress.propeli.security.domain.CustomClaims;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JWTUtil {
	public static final String CUSTOM_CLAIM_KEY = "customClaim";
	private static final String ISSUER = "safexpress";
	private static final String API_KEY = "safexpress-key";

	private JWTUtil() {
	}

	public static String createJWT(String subject, CustomClaims customClaims) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date(System.currentTimeMillis())).setSubject(subject).setIssuer(ISSUER)
				.claim(CUSTOM_CLAIM_KEY, customClaims).signWith(signatureAlgorithm, signingKey);

		return builder.compact();
	}

	public static Claims parseJWT(String jwt) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY)).parseClaimsJws(jwt).getBody();
	}

}
