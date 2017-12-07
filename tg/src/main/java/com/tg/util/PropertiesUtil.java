package com.tg.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/27.
 */
public class PropertiesUtil {
    public static Properties load(String file) throws IOException {
        return load(new File(file));
    }


    public static Properties load(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Properties p = new Properties();
        p.load(fis);
        fis.close();
        return p;
    }

    public static Properties loadClassLoader(String filename){
        Properties prop = new Properties();
        try {
            InputStream in = null;
            try {
                in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
                prop.load(in);
                }finally {
                    if(in != null){
                        in.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return prop;
    }


    public static Properties[] load(String... files) throws IOException {
        Properties[] result = new Properties[files.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = load(files[i]);
        }
        return result;
    }


    public static Properties[] load(File... files) throws IOException {
        Properties[] result = new Properties[files.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = load(files[i]);
        }
        return result;
    }


    public static Properties[] loadAllFromDirectory(String dir) throws IOException {
        return loadAllFromDirectory(new File(dir), false);
    }


    public static Properties[] loadAllFromDirectory(File dir) throws IOException {
        return loadAllFromDirectory(dir, false);
    }


    public static Properties[] loadAllFromDirectory(String dir, boolean recursive) throws IOException {
        return loadAllFromDirectory(new File(dir), recursive);
    }


    public static Properties[] loadAllFromDirectory(File dir, boolean recursive) throws IOException {
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"properties"}, recursive);
        return load(files.toArray(new File[files.size()]));
    }
}
