package com.service.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SaltSecret
{

	private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
	
	private static final byte[] SALT =
	{
		(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
		(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
	};


    public static String encrypt(String pass) throws GeneralSecurityException, UnsupportedEncodingException
    {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        //SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pass.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(pass.getBytes("UTF-8")));
    }

    private static String base64Encode(byte[] bytes)
    {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Encoder().encode(bytes);
    }

    public static String decrypt(String pass) throws GeneralSecurityException, IOException 
    {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        //SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pass.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(pass)), "UTF-8");
    }

    private static byte[] base64Decode(String pass) throws IOException 
    {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Decoder().decodeBuffer(pass);
    }

}