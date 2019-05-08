package com.ctl.jni;

/**
 * <p>Title: RSAUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * <p>rsa私钥公钥生成工具 下载地址 https://docs.open.alipay.com/291/106097/   也可以使用本工具类生成</p>
 * <p><p>
 * @author guolin
 * @version 1.0
 * @date 2019-04-30 13:54
 */

import com.alibaba.fastjson.JSON;
//import com.hanshow.wise.base.common.util.ConfigUtils;
//import com.hanshow.wise.base.privileges.listener.HanshowLicence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 */
public class RSAUtil {
    private static Logger logger = LoggerFactory.getLogger(RSAUtil.class);
    /**
     * 生成公钥私钥
     * @return
     */
    public static RSAKey genKey() {
        return genKey(1024);
    }

    /**
     * 生成公钥私钥
     * @param keysize 密钥大小为(位)
     * @return
     */
    public static RSAKey genKey(Integer keysize) {
        try {
            //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            //初始化密钥对生成器，密钥大小为1024位
            keyPairGen.initialize(keysize);
            //生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            //得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            //得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeyString = new String(Base64Utils.encodeToString(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64Utils.encodeToString((privateKey.getEncoded())));
            RSAKey rsaKey = new RSAKey();
            rsaKey.setPrivateKeyString(privateKeyString);
            rsaKey.setPublicKeyString(publicKeyString);
            rsaKey.setPublicKey(publicKey);
            rsaKey.setPrivateKey(privateKey);
            return rsaKey;
        } catch (Exception e) {
            logger.error("获取公钥私钥失败", e);
            return null;
        }
    }

    /**
     * 获取私钥
     *
     * @param privateKeyBase64 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKeyBase64) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64Utils.decodeFromString(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKeyBase64 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKeyBase64) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64Utils.decodeFromString(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 加密
     *
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws Exception
     */
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws Exception {
        if (publicKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 加密
     *
     * @param publicKeyStr
     * @param srcBytes
     * @return
     * @throws Exception
     */
    protected String encrypt(String publicKeyStr, byte[] srcBytes) throws Exception {
        if (publicKeyStr != null) {
            PublicKey publicKey = getPublicKey(publicKeyStr);
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return Base64Utils.encodeToString(resultBytes);
        }
        return null;
    }
    /**
     * 加密
     *
     * @param publicKeyStr
     * @param srcString
     * @return
     * @throws Exception
     */
    public String encrypt(String publicKeyStr, String srcString) throws Exception {
        return encrypt(publicKeyStr,srcString,"utf-8");
    }
    public String encrypt(String publicKeyStr, String srcString,String charset) throws Exception {
        if (publicKeyStr != null) {
            PublicKey publicKey = getPublicKey(publicKeyStr);
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcString.getBytes(charset));
            return Base64Utils.encodeToString(resultBytes);
        }
        return null;
    }
    /**
     * 解密
     *
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws Exception
     */
    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws Exception {
        if (privateKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 解密
     *
     * @param privateKeyStr
     * @param srcBytes
     * @return
     * @throws Exception
     */
    public String decrypt(String privateKeyStr, byte[] srcBytes) throws Exception {
        if (privateKeyStr != null) {

            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return Base64Utils.encodeToString(resultBytes);
        }
        return null;
    }
    /**
     * 解密
     *
     * @param privateKeyStr
     * @param srcStr
     * @return
     * @throws Exception
     */
    public String decrypt(String privateKeyStr, String srcStr) throws Exception {
        if (privateKeyStr != null) {

            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(Base64Utils.decodeFromString(srcStr));
            return Base64Utils.encodeToString(resultBytes);
        }
        return null;
    }


    public static void main(String[] args) throws Exception {

        String msg = "待加密str=123";
        RSAKey rsaKey = genKey();
        logger.info(JSON.toJSONString(rsaKey));
        RSAUtil rsa = new RSAUtil();
        //用公钥加密
        byte[] srcBytes = msg.getBytes();
        byte[] resultBytes = rsa.encrypt(rsaKey.getPublicKey(), srcBytes);
        String resultString = rsa.encrypt(rsaKey.getPublicKeyString(), srcBytes);
        String resultString1 = rsa.encrypt(rsaKey.getPublicKeyString(), msg);

        //用私钥解密
        byte[] decBytes = rsa.decrypt(rsaKey.getPrivateKey(), resultBytes);
        String decString = rsa.decrypt(rsaKey.getPrivateKeyString(), resultString);
        String decString1 = rsa.decrypt(rsaKey.getPrivateKeyString(), resultString1);
        System.out.println("明文是:" + msg);
        System.out.println("加密后是:" + Base64Utils.encodeToString(resultBytes));
        System.out.println("加密后是:" + resultString);
        System.out.println("加密后是:" + resultString1);
        System.out.println("解密后是:" + new String(decBytes));
        System.out.println("解密后是:" + new String(Base64Utils.decodeFromString(decString)));
        System.out.println("解密后是:" + new String(Base64Utils.decodeFromString(decString1)));
        String encryptStr="";
//        System.out.println("加密后是:" + (encryptStr=rsa.encrypt(ConfigUtils.getType("public_key"),msg)));
//        System.out.println("解密后是:" + new String(Base64Utils.decodeFromString( rsa.decrypt(ConfigUtils.getType("private_key"),encryptStr))));
//        HanshowLicence hanshowLicence = new HanshowLicence();
//        hanshowLicence.setDeviceNum(1000);
//        hanshowLicence.setStoreNum(100);
//        String licenceMsg = JSON.toJSONString(hanshowLicence);
//        System.out.println("明文是:" + licenceMsg);
//        String licenceEncryptStr=rsa.encrypt(ConfigUtils.getType("public_key"),licenceMsg);
//        System.out.println("加密后是:" + licenceEncryptStr);
//        String licenceDecryptStr = new String(Base64Utils.decodeFromString(rsa.decrypt(ConfigUtils.getType("private_key"), licenceEncryptStr)));
//        System.out.println("解密后是:" + licenceDecryptStr);
    }

}
