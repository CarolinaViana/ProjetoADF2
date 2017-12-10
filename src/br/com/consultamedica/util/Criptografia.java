package br.com.consultamedica.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	private static final String CRIPTOGRAFIA_SHA_256 = "SHA-256";

	public static String criptografar(String mensagem) throws NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance(CRIPTOGRAFIA_SHA_256);

		byte[] mensagemEmBytes = messageDigest.digest(mensagem.getBytes());

		return getBytesComoString(mensagemEmBytes);

	}

	private static String getBytesComoString(byte[] mensagemEmBytes) {

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < mensagemEmBytes.length; i++) {
			String hex = Integer.toHexString(0xff & mensagemEmBytes[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
