//package com.xiong.core.utils;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//
//import org.apache.commons.lang3.StringUtils;
//
//public class ASCUtil {
//private static final String DES_ALGORITHM = "DES";
//private static final String KEY="uhome-des-key";
//		
//	/**
//	 * @param plainData
//	 * @return
//	 * @throws Exception
//	 */
//	public String encryption(String plainData) throws Exception{
//
//		Cipher cipher = null;
//		try {
//			cipher = Cipher.getInstance(DES_ALGORITHM);
//			cipher.init(Cipher.ENCRYPT_MODE, generateKey());
//			
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		}catch(InvalidKeyException e){
//			
//		}
//		
//		try {
//			// Ϊ�˷�ֹ����ʱ��javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher�쳣��
//			// ���ܰѼ��ܺ���ֽ�����ֱ��ת�����ַ���
//			byte[] buf = cipher.doFinal(plainData.getBytes());
//			
//			return Base64Utils.encode(buf);
//			
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//			throw new Exception("IllegalBlockSizeException", e);
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//			throw new Exception("BadPaddingException", e);
//		}
//	    
//	}
//
//	/**
//	 * DES����
//	 * @param secretData
//	 * @param secretKey
//	 * @return
//	 * @throws Exception
//	 */
//	public static String decryption(String secretData) throws Exception{
//		
//		Cipher cipher = null;
//		try {
//			cipher = Cipher.getInstance(DES_ALGORITHM);
//			cipher.init(Cipher.DECRYPT_MODE, generateKey());
//			
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			throw new Exception("NoSuchAlgorithmException", e);
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//			throw new Exception("NoSuchPaddingException", e);
//		}catch(InvalidKeyException e){
//			e.printStackTrace();
//			throw new Exception("InvalidKeyException", e);
//			
//		}
//		
//		try {
//			
//			byte[] buf = cipher.doFinal(Base64Utils.decode(secretData.toCharArray()));
//			
//			return new String(buf);
//			
//		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
//			throw new Exception("IllegalBlockSizeException", e);
//		} catch (BadPaddingException e) {
//			e.printStackTrace();
//			throw new Exception("BadPaddingException", e);
//		}
//	}
//	
//	/**
//	 * �����Կ
//	 * 
//	 * @param secretKey
//	 * @return
//	 * @throws NoSuchAlgorithmException 
//	 * @throws InvalidKeyException 
//	 * @throws InvalidKeySpecException 
//	 */
//	private static SecretKey generateKey() throws NoSuchAlgorithmException,InvalidKeyException,InvalidKeySpecException{
//
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
//		DESKeySpec keySpec = new DESKeySpec(KEY.getBytes());
//		keyFactory.generateSecret(keySpec);
//		return keyFactory.generateSecret(keySpec);
//	}		
//	
//	
//	
//	public static void main(String[] args) throws Exception{
//		
////		ASCUtil des = new ASCUtil();
////		System.out.println("--------------");
////		System.out.println("input----->>:"+args[0]);
////		String result = des.encryption(args[0]);
////		System.out.println("encryp----->>:"+result);
////		System.out.println("decrypt----->>:"+decryption(result));
////		jdbc.username=NjekwJ99+po=
////		jdbc.password=+RTYb/VgcvMYvBsivIz/tw==
//
//		
//		
//				
//		
//		ASCUtil des = new ASCUtil();
//		System.out.println("--------------");
////		String result = des.encryption("+RTYb/VgcvMYvBsivIz/tw==");
////		System.out.println("encryp----->>:"+result);
////		System.out.println("decrypt----->>:"+decryption("NjekwJ99+po="));
////		System.out.println("decrypt----->>:"+decryption("+RTYb/VgcvMYvBsivIz/tw=="));
//		//user_supp_dept Uoz2952_wy762
//		System.out.println(decryption("pl+uhZFRce3K4Xoiux2BWg=="));
//		System.out.println(decryption("pl+uhZFRce0FOFhyDKF/Xw=="));
//		System.out.println("medApp--->"+des.encryption("knowApp"));
//		System.out.println("732_Rsrxr3su7--->"+des.encryption("aXrXU3c83q"));
//
//		
//		/*result = decryption("k+IRHtpG8hI=");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("0SE0jRMPz/Pit2QB2dBUZw==");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("k+IRHtpG8hI=");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("0SE0jRMPz/Pit2QB2dBUZw==");
//		System.out.println("encryp----->>:"+result);*/
//		
//	}
//	
//	
//		
//	static class Base64Utils {
//
//		static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
//		static private byte[] codes = new byte[256];
//		static {
//			for (int i = 0; i < 256; i++)
//				codes[i] = -1;
//			for (int i = 'A'; i <= 'Z'; i++)
//				codes[i] = (byte) (i - 'A');
//			for (int i = 'a'; i <= 'z'; i++)
//				codes[i] = (byte) (26 + i - 'a');
//			for (int i = '0'; i <= '9'; i++)
//				codes[i] = (byte) (52 + i - '0');
//			codes['+'] = 62;
//			codes['/'] = 63;
//		}
//		
//		/**
//		 * ��ԭʼ���ݱ���Ϊbase64����
//		 */
//		static public String encode(byte[] data) {
//			char[] out = new char[((data.length + 2) / 3) * 4];
//			for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
//				boolean quad = false;
//				boolean trip = false;
//				int val = (0xFF & (int) data[i]);
//				val <<= 8;
//				if ((i + 1) < data.length) {
//					val |= (0xFF & (int) data[i + 1]);
//					trip = true;
//				}
//				val <<= 8;
//				if ((i + 2) < data.length) {
//					val |= (0xFF & (int) data[i + 2]);
//					quad = true;
//				}
//				out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
//				val >>= 6;
//				out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
//				val >>= 6;
//				out[index + 1] = alphabet[val & 0x3F];
//				val >>= 6;
//				out[index + 0] = alphabet[val & 0x3F];
//			}
//			
//			return new String(out);
//		}
//
//		/**
//		 * ��base64��������ݽ����ԭʼ����
//		 */
//		static public byte[] decode(char[] data) {
//			int len = ((data.length + 3) / 4) * 3;
//			if (data.length > 0 && data[data.length - 1] == '=')
//				--len;
//			if (data.length > 1 && data[data.length - 2] == '=')
//				--len;
//			byte[] out = new byte[len];
//			int shift = 0;
//			int accum = 0;
//			int index = 0;
//			for (int ix = 0; ix < data.length; ix++) {
//				int value = codes[data[ix] & 0xFF];
//				if (value >= 0) {
//					accum <<= 6;
//					shift += 6;
//					accum |= value;
//					if (shift >= 8) {
//						shift -= 8;
//						out[index++] = (byte) ((accum >> shift) & 0xff);
//					}
//				}
//			}
//			if (index != out.length)
//				throw new Error("miscalculated data length!");
//			return out;
//		}
//	}
//}
