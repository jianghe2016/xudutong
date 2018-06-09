package com.xdt.xudutong.rsa;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Administrator on 2018/5/2.
 */

public class RSAUtils {

    private static String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding"; // 加密算法
    private static int KEYBIT = 2048;// 密钥位数
    private static int RESERVEBYTES = 11;// 加密block需要预留字节数
    private static int decryptBlock = KEYBIT / 8; // 每段解密block数，256 bytes
    private static int encryptBlock = decryptBlock - RESERVEBYTES; // 每段加密block数245bytes

    /**
     * 获取公钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 获取私钥
     *
     * @param key
     *            pkcs#8
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static String getKeyString(Key key) {
        byte[] keyBytes = key.getEncoded();
        String s = Base64.encodeToString(keyBytes, Base64.DEFAULT);
        return s;
    }

    /**
     * 加密
     *
     * @param key
     *            公钥
     * @param data
     *            加密报文 getBytes()
     * @return
     */
    public static String encode(String pubKey, byte[] data) {
        // 计算分段加密的block数 (向上取整)
        int nBlock = (data.length / encryptBlock);
        if ((data.length % encryptBlock) != 0) { // 余数非0，block数再加1
            nBlock += 1;
        }
        // 输出buffer, 大小为nBlock个decryptBlock
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * decryptBlock);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            PublicKey publicKey = getPublicKey(pubKey);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            for (int offset = 0; offset < data.length; offset += encryptBlock) {
                // block大小: encryptBlock 或剩余字节数
                int inputLen = (data.length - offset);
                if (inputLen > encryptBlock) {
                    inputLen = encryptBlock;
                }
                // 得到分段加密结果
                byte[] encryptedBlock = cipher.doFinal(data, offset, inputLen);
                // 追加结果到输出buffer中
                outbuf.write(encryptedBlock);
            }
            return Base64.encodeToString(outbuf.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outbuf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解密
     *
     * @param priKey
     *            pkcs#8
     * @param data
     *            解密报文 "string".getBytes();
     * @return new String( byte[] )
     */
    public static byte[] decode(String priKey, byte[] data) {
        // 计算分段加密的block数 (向上取整)
        int nBlock = (data.length / encryptBlock);
        if ((data.length % encryptBlock) != 0) { // 余数非0，block数再加1
            nBlock += 1;
        }
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * encryptBlock);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            PrivateKey privateKey = getPrivateKey(priKey);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            for (int offset = 0; offset < data.length; offset += decryptBlock) {
                // block大小: decryptBlock 或剩余字节数
                int inputLen = (data.length - offset);
                if (inputLen > decryptBlock) {
                    inputLen = decryptBlock;
                }
                // 得到分段加密结果
                byte[] decryptedBlock = cipher.doFinal(data, offset, inputLen);
                // 追加结果到输出buffer中
                outbuf.write(decryptedBlock);
            }
            return outbuf.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outbuf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 普通解密
     * @param key
     * @param data
     * @return new String()
     */
    public static byte[] decode(String key, String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            PrivateKey privateKey = getPrivateKey(key);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(Base64.decode(data.getBytes(), Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 普通加密
     * @param key
     * @param data getBytes()
     * @return Bsae64.enCodeToString()
     */
    public static byte[] encode2(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            PublicKey publicKey = getPublicKey(key);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
