package com.ii.biz.common.common.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by liyou on 17/4/21.
 */
public class ErrorResoures {

    private static Logger logger         = LoggerFactory.getLogger(ErrorResoures.class);

    private static Properties errorCode = new Properties();

    /**
     * 使用static代码块，在系统启动时，从文件中初始.
     */
    static {
        try (InputStreamReader reader = new InputStreamReader(ErrorResoures.class.getClassLoader()
                .getResourceAsStream("error/errorCode.properties"), "UTF-8")) {
            errorCode.load(reader);
        } catch (FileNotFoundException e) {
            logger.error("找不到文件:{}", e);
        } catch (IOException e) {
            logger.error("读取文件错误:{}", e);
        }

    }

    public static String getErrorCode(String key){
        return errorCode.getProperty(key);
    }

    public static void main(String[] strs){
        System.out.println(getErrorCode("retry"));
    }


}
