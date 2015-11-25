/**
 * This file created at 2015-4-17.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */
package com.tbetl.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;

/**
 * <code>{@link AES}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
public class AES {
	
	//原文：avkO/TDP5t8kzQfmJ3aa9kVSwh2V1fG/piEMaL/LcPk=
	//密钥：12345678123456781234567812345678
	//密文：W9cXwZnkSaE6WwMjGN0BMeS5lGG5FRh+Q+NSmGBaNSgOQiOEwhkiXhm0sSA4vISK


	public static void main(String ...strings ) throws Exception{
		System.out.println("原文： fV0JMnaKVgeH9xnRyQ1xrkVSwh2V1fG/piEMaL/LcPk="+"\n"+"密钥：12345678123456781234567812345678");
		System.out.println("密文: "+encrypt("12345678123456781234567812345678", "fV0JMnaKVgeH9xnRyQ1xrkVSwh2V1fG/piEMaL/LcPk="));
		System.out.println("解密: "+Decrypt("12345678123456781234567812345678", "t6m0ycdjK+1MFK4oOwbQWOx/Ds9N5uenMB+1CMX97+dbKHQNuPKvRB1yra0wYGih"));
//		System.out.println(aesEncrypt("12345678123456781234567812345678", "uciDHjqQi+3vbL/KO+MDEgLvP8ETAuTm86cdOVD0H7I3JmSGPG7hXsfXGrIf/w3s"));
		
	}
	
	   // 解密    
	   public static String Decrypt(String sKey,String sSrc) throws Exception {    
	       try {    
	           SecretKeySpec skeySpec = getKey(sKey);  
	           Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");    
//	           IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());    
	           cipher.init(Cipher.DECRYPT_MODE, skeySpec);    
	           byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用bAES64解密    
               byte[] original = cipher.doFinal(encrypted1);    
               String originalString = new String(original);    
               return originalString;    
	       } catch (Exception ex) {    
	           System.out.println(ex.toString());    
	           return null;    
	       }    
	   }
	   
	   private static SecretKeySpec getKey(String strKey) throws Exception {
			byte[] arrBTmp = strKey.getBytes();
			byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
			for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
				arrB[i] = arrBTmp[i];
			}
			SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

			return skeySpec;
		}

	   //加密
	   public static String encrypt(String strKey, String strIn) throws Exception {
		   	MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(strKey.getBytes("utf-8"));
//			SecretKeySpec skeySpec = new SecretKeySpec(digest.digest(), 0, 16,"AES");
			SecretKeySpec skeySpec = getKey(strKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(strIn.getBytes());
			return new BASE64Encoder().encode(encrypted);
		}
	   
//	   public static String encrypt2(String strKey, String strIn) throws Exception{
//		   KeyGenerator kgen = KeyGenerator.getInstance("AES");  
//           kgen.init(128, new SecureRandom(password.getBytes()));  
//           SecretKey secretKey = kgen.generateKey();  
//           byte[] enCodeFormat = secretKey.getEncoded();  
//           SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
//           Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器   
//          cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
//          byte[] result = cipher.doFinal(content);  
//          return result; // 加密   
//	   }
}
