package com.service.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * use of project of aes-example of github. url is
 * https://github.com/mpetersen/aes-example
 * 
 * 
 */
// TODO: Implement 256-bit version like:
// http://securejava.wordpress.com/2012/10/25/aes-256/
public class AesUtil
{
	private final int keySize;
	private final int iterationCount;
	private Cipher cipher = null;

	public AesUtil( int keySize, int iterationCount )
	{
		this.keySize = keySize;
		this.iterationCount = iterationCount;
		try
		{
			cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
		}
		catch ( NoSuchAlgorithmException e )
		{
			throw fail( e );
		}
		catch ( NoSuchPaddingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String encrypt( String salt, String iv, String passphrase, String plaintext )
	{
		try
		{
			SecretKey key = generateKey( salt, passphrase );
			byte[] encrypted = doFinal( Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes( "UTF-8" ) );
			return base64( encrypted );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw fail( e );
		}
	}

	public String decrypt( String salt, String iv, String passphrase, String ciphertext )
	{
		try
		{
			SecretKey key = generateKey( salt, passphrase );
			byte[] decrypted = doFinal( Cipher.DECRYPT_MODE, key, iv, base64( ciphertext ) );
			return new String( decrypted, "UTF-8" );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw fail( e );
		}
	}

	private byte[] doFinal( int encryptMode, SecretKey key, String iv, byte[] bytes )
	{
		byte[] temp = null;
		try
		{
			cipher.init( encryptMode, key, new IvParameterSpec( hex( iv ) ) );
			temp = cipher.doFinal( bytes );
		}
		catch ( InvalidKeyException e )
		{
			throw fail( e );
		}
		catch ( InvalidAlgorithmParameterException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IllegalBlockSizeException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( BadPaddingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	private SecretKey generateKey( String salt, String passphrase )
	{
		SecretKey keyval = null;
		try
		{
			SecretKeyFactory factory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );
			KeySpec spec = new PBEKeySpec( passphrase.toCharArray(), hex( salt ), iterationCount, keySize );
			SecretKey key = new SecretKeySpec( factory.generateSecret( spec ).getEncoded(), "AES" );
			keyval = key;
		}
		catch ( NoSuchAlgorithmException e )
		{
			throw fail( e );
		}
		catch ( InvalidKeySpecException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keyval;
	}

	public static String random( int length )
	{
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes( salt );
		return hex( salt );
	}

	public static String base64( byte[] bytes )
	{
		return Base64.encodeBase64String( bytes );
	}

	public static byte[] base64( String str )
	{
		return Base64.decodeBase64( str );
	}

	public static String hex( byte[] bytes )
	{
		return Hex.encodeHexString( bytes );
	}

	public static byte[] hex( String str )
	{
		try
		{
			return Hex.decodeHex( str.toCharArray() );
		}
		catch ( DecoderException e )
		{
			throw new IllegalStateException( e );
		}
	}

	private IllegalStateException fail( Exception e )
	{
		return new IllegalStateException( e );
	}
}
