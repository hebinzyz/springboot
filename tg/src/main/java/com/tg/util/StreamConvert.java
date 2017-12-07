package com.tg.util;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by Administrator on 2017/7/27.
 */
public class StreamConvert {

    public final static String CHAR_SET = "utf-8";

    /**
     * 输入流转为string
     * @param inputStream
     * @return
     */
    public static String getInputStreamString(InputStream inputStream) {
        StringBuffer resu = new StringBuffer();
        byte[] b = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(b)) != -1) {
                resu.append(new String(b, 0, len, CHAR_SET));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resu.toString();
    }
}
