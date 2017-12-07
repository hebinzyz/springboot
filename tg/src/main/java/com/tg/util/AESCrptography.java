package com.tg.util;



import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCrptography {

    static String e = "9238513401340235";

    // 加密
    public static String Encrypt(String src, String key) throws Exception {
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"0102030405060708
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(src.getBytes());

        //return Base64.encodeBytes(encrypted);
        // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 解密
    public static String Decrypt(String src, String key) throws Exception {
        // 判断Key是否正确
        if (key == null) {
            //System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            //System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = Base64.getDecoder().decode(src);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
    }

    public static void main(String[] args) throws Exception {
        String key = "r!Gh8r10ymD34p21";
        System.out.println(key);
        String src = "{\"dataList\":[{\"10\":\"0\",\"11\":\"0\",\"12\":\"0\",\"13\":\"0\",\"14\":\"0\",\"15\":\"0\",\"16\":\"0\",\"17\":\"0\",\"18\":\"0\",\"19\":\"0\",\"20\":\"0\",\"21\":\"0\",\"22\":\"0\",\"23\":\"0\",\"24\":\"0\",\"25\":\"0\",\"26\":\"0\",\"27\":\"0\",\"28\":\"0\",\"29\":\"0\",\"30\":\"0\",\"31\":\"0\",\"32\":\"0\",\"33\":\"0\",\"34\":\"0\",\"35\":\"0\",\"36\":\"0\",\"37\":\"0\",\"38\":\"0\",\"39\":\"0\",\"40\":\"0\",\"41\":\"0\",\"42\":\"0\",\"43\":\"0\",\"44\":\"0\",\"45\":\"0\",\"46\":\"0\",\"47\":\"0\",\"48\":\"0\",\"49\":\"0\",\"50\":\"0\",\"51\":\"0\",\"01\":\"0\",\"02\":\"0\",\"03\":\"0\",\"04\":\"0\",\"05\":\"0\",\"06\":\"0\",\"07\":\"0\",\"08\":\"0\",\"09\":\"0\",\"terminalId\":\"PCMII1400100789\",\"getTime\":\"20170715000000\",\"htype\":\"IA\"}],\"companyId\":\"1989\",\"dataType\":\"02\"}";
        System.out.println(src);
        // 加密
        long start = System.currentTimeMillis();
        String enString = AESCrptography.Encrypt(src, key);
        System.out.println("加密后的字串是：" + enString);

        long useTime = System.currentTimeMillis() - start;
        System.out.println("加密耗时：" + useTime + "毫秒");

        // 解密
        start = System.currentTimeMillis();
        String DeString = AESCrptography.Decrypt(enString, key);
        System.out.println("解密后的字串是：" + DeString);
        useTime = System.currentTimeMillis() - start;
        System.out.println("解密耗时：" + useTime + "毫秒");
    }
}
