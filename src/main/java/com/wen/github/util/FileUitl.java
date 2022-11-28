package com.wen.github.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author cuiwl
 * @date 2022-11-23 10:20
 */
public class FileUitl {

    public static final String TEMPLATE_FILETYPE = ".copy";

    public static File[] getCopyTemplates(String templatePath) {
        File file = new File(templatePath);
        if(!file.exists()) {
            throw new RuntimeException("模板文件不存在");
        }
        if(file.isDirectory()) {
            File[] files = file.listFiles(filepath -> {
                if(filepath.getName().endsWith(TEMPLATE_FILETYPE)) {
                    return true;
                }
                return false;
            });
            return files;
        }
        return null;
    }

    public static String getFileContent(String filePath) {
        StringBuilder sb = new StringBuilder();
        FileInputStream input = null;
        try {
            //打开文件输入流
            input = new FileInputStream(filePath);
            byte[] temp = new byte[1024];
            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
