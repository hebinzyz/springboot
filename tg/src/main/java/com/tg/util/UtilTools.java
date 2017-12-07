package com.tg.util;


import java.security.MessageDigest;

/**
 * @author liyu
 * @since 2017-07-19
 * 工具类
 */
public class UtilTools {
    public static void main(String[] args) throws Exception {
        String appkey = "4if69O9PI0bpFsrAaFyEsQty";
        String nonce = "Lc9KcJGi18";
        String timestamp = "1500434502";
        String md5_encrypt_str = MD5(appkey);
        String secretKey = SHA1(md5_encrypt_str + nonce + timestamp);
        System.out.print(secretKey);
    }

    /**
     * 检查宁夏查询接口secretKey:sha1(md5(appkey) + nonce + timestamp)
     *
     * @param appKey    申请appId获得的key
     * @param nonce     随机字符串
     * @param timestamp 时间戳
     * @param check_str 调用方上传secretKey
     * @return true:secretKey匹配，false:secretKey不匹配
     */
    public static boolean checkSecretKey(String appKey, String nonce, String timestamp, String check_str) {
        String md5_encrypt_str = MD5(appKey);
        String secretKey = SHA1(md5_encrypt_str + nonce + timestamp);
        return check_str.equals(secretKey);
    }

    /**
     * md5加密字符串
     *
     * @param encrypt_str 需要加密的字符串
     * @return md5(encode_str)
     */
    public final static String MD5(String encrypt_str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = encrypt_str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * sha1字符串加密
     *
     * @param str 需要加密的字符串
     * @return sha1(encode_str)
     */
    public static String SHA1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }

    }


}
