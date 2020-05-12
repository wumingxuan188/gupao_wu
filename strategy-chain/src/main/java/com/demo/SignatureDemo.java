package com.demo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;

public class SignatureDemo {
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String HMAC_SHA1 = "HmacSHA1";
	
	/**
	 * 计算参数的签名值 demo
	 * @param paramsMap 所有的参数Map
	 * @param seed 如果是服务端接入参数是app_secret+server_static_key，客户端是app_secret
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 */
	public  String caculateSignature(Map<String, String> paramsMap, String seed) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if(paramsMap == null || paramsMap.isEmpty() || StringUtils.isEmpty(seed)) {
			throw new IllegalArgumentException("paramsMap and seed should not empty");
		}
		
		if(!(paramsMap instanceof TreeMap<?, ?>)) {
			paramsMap = new TreeMap<String, String> (paramsMap);
		}
		StringBuilder paramsBuilder = new StringBuilder();
		for(Map.Entry<String, String> entry: paramsMap.entrySet()) {
			paramsBuilder.append(entry.getKey());
			paramsBuilder.append("=");
			paramsBuilder.append(entry.getValue());
			paramsBuilder.append("&");
		}
		paramsBuilder.deleteCharAt(paramsBuilder.length()-1);
		String paramsEncodedStr = base64Encode(paramsBuilder.toString());
		String sig = hmacSHA1ToStr(paramsEncodedStr, seed);
		System.err.println(String.format("caculateSignature, paramsMap: %s, paramsEncodedStr: %s, seed: %s, caculated sig: %s", paramsMap, paramsEncodedStr,
				seed, sig));
		return sig;
	}

	/**
	 * HMAC SHA1签名或摘要算法
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	private String hmacSHA1ToStr(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		if(data == null || key == null) {
			return null;
		}
		return hmacSHA1ToStr(data.getBytes(), key.getBytes());
	}

		/**
	 * HMAC SHA1签名或摘要算法
	 * @param data 待摘要的字节数据
	 * @param key  使用的key
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	private String hmacSHA1ToStr(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException  {
		System.err.println("MD5之前:"+hmacSHA1(data, key));
		return DigestUtils.md5Hex(hmacSHA1(data, key));
	}

	private byte[] hmacSHA1(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		if(data == null || key == null) {
			return null;
		}

		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		return mac.doFinal(data);
	}

	/**
	 * Base64编码
	 * @param data 字符串数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String base64Encode(String data) throws UnsupportedEncodingException {
		if(data == null) {
			return null;
		}
		return Base64.encodeBase64String(data.getBytes(DEFAULT_ENCODING));
	}

	public static void main(String[] args) throws Exception {
		String encode = base64Encode("access_account_type=3&app_key=99b37417e1185eda1378600593b45c40&client_os_type=1&device_id=5de5359168c511ea9113b25216652135&distribute_item_type=3&mobile=18311457149&nonce=5de50e8068c511ea97b2b25216652135&pay_channel=3&pay_content=1010500100000306120&server_api_version=1.0.0&third_order_no=11111111111111111111&timestamp=1584500748137");
		//System.err.println(encode);
		SignatureDemo demo = new SignatureDemo();
		String str = demo.hmacSHA1ToStr(encode, "dd7a46b12fe8a304ef17892c89ede22ahdFOWK39");
		System.err.println("==========================");
		System.err.println(str);
	}

}
