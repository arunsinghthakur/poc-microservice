package com.safexpress.propeli.security.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurityUtil {

	private SecurityUtil() {
	}

	public static String encryptedPassword(String password, String salt) {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] byteSalt = salt.getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, byteSalt, iterations, 64 * 8);
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return toHex(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return salt;

	}

	public static String generateSalt() {
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return new String(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean isPasswordMatch(String plainPwd, String encryptedPwd, String salt) {
		return encryptedPwd.equals(encryptedPassword(plainPwd, salt));
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public static void main(String[] args) {
		String salt = generateSalt();
		System.out.println(isPasswordMatch("joker", encryptedPassword("joker", salt), salt));
	}
}
