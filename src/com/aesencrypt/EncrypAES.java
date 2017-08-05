package com.aesencrypt;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class EncrypAES implements Serializable {

    // KeyGenerator 提供對稱密鑰生成器的功能，支持各種演算法
    private KeyGenerator keygen;
    // SecretKey 負責保存對稱密鑰
    private SecretKey deskey;
    // Cipher 負責完成加密或解密工作
    private Cipher c;
    //該字節數組負責保存加密的結果
    private byte[] cipherByte;

    public EncrypAES()
        throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 實例化支持 AES 演算法的密鑰生成器(演算法名稱命名需按規定，否則拋出異常)
        keygen = KeyGenerator.getInstance("AES");
        //生成密鑰
        deskey = keygen.generateKey();
        //生成 Cipher 物件,指定其支持的 AES 演算法
        c = Cipher.getInstance("AES");
    }

    /**
     * 對字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Encrytor(String str)
        throws InvalidKeyException, IllegalBlockSizeException,
               BadPaddingException
    {
        // 根據密鑰，對 Cipher 物件進行初始化，ENCRYPT_MODE 表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，結果保存進 cipherByte
        cipherByte = c.doFinal(src);
        return cipherByte;
    }

    
    public String Encrytor2(String str)
            throws InvalidKeyException, IllegalBlockSizeException,
                   BadPaddingException
        {
            // 根據密鑰，對 Cipher 物件進行初始化，ENCRYPT_MODE 表示加密模式
            c.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] src = str.getBytes();
            // 加密，結果保存進 cipherByte
            cipherByte = c.doFinal(src);
            return toHexString(cipherByte);
        }
    
    public String Encrytor3(String str,SecretKey deskey)
            throws InvalidKeyException, IllegalBlockSizeException,
                   BadPaddingException
        {
            // 根據密鑰，對 Cipher 物件進行初始化，ENCRYPT_MODE 表示加密模式
            c.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] src = str.getBytes();
            // 加密，結果保存進 cipherByte
            cipherByte = c.doFinal(src);
            return toHexString(cipherByte);
        }
    
    /**
     * 對字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Decryptor(byte[] buff)
        throws InvalidKeyException, IllegalBlockSizeException,
               BadPaddingException
    {
        // 根據密鑰，對 Cipher 物件進行初始化，DECRYPT_MODE 表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return cipherByte;
    }

    
    public String Decryptor2(byte[] buff)
            throws InvalidKeyException, IllegalBlockSizeException,
                   BadPaddingException
        {
            // 根據密鑰，對 Cipher 物件進行初始化，DECRYPT_MODE 表示加密模式
            c.init(Cipher.DECRYPT_MODE, deskey);
            cipherByte = c.doFinal(buff);
            return toHexString(cipherByte);
        }
    
    
    public String Decryptor3(byte[] buff,SecretKey deskey)
            throws InvalidKeyException, IllegalBlockSizeException,
                   BadPaddingException
        {
            // 根據密鑰，對 Cipher 物件進行初始化，DECRYPT_MODE 表示加密模式
            c.init(Cipher.DECRYPT_MODE, deskey);
            cipherByte = c.doFinal(buff);
            return toHexString(cipherByte);
        }
    
    
    private String toHexString(byte[] in) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < in.length; i++){
            String hex = Integer.toHexString(0xFF & in[i]);
            if (hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    

    
    /**
     * @param args
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
        EncrypAES de1 = new EncrypAES();
        String pwd ="c83758341";
        
        UUID uuid = UUID.randomUUID();
        String salt = uuid.toString().replace("-", "");
        System.out.println("salt:"+salt);
        String pwdInDB=de1.Encrytor2(pwd+salt);
        System.out.println(pwdInDB);
        
        
        String userKeyIn="c83758341";
        System.out.println("使用者輸入:"+userKeyIn);
        
        
        System.out.println("");
        System.out.println("");

        
        
        System.out.println("============使用de1================");
        String encryptUserKeyIn = de1.Encrytor2(userKeyIn+salt); 
        System.out.println("encryptUserKeyIn:"+encryptUserKeyIn);
        System.out.println("輸入結果"+encryptUserKeyIn.equals(pwdInDB));
        System.out.println("==================================");
        

        
        System.out.println("");
        System.out.println("");
        
        System.out.println("以下始輸出");
        byte[] keyBytes = de1.deskey.getEncoded();
        String encodedKey = new String(Base64.getEncoder().encode(keyBytes) ,"UTF-8");
        System.out.print(encodedKey);
//        byte[] encontent = de1.Encrytor(msg);
//        byte[] decontent = de1.Decryptor(encontent);
//        System.out.println("明文是:" + msg);
//        System.out.println("加密後:" + new String(encontent));
//        System.out.println("解密後:" + new String(decontent));
    }
}