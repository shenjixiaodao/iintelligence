package com.ii.iintelligence.api.controller.vo;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class PayInfoResult extends WebResult {
    /**
     * OK("成功"), FAILED("失败"), UNKNOWN("未明"), CREATED("初创")
     */
    @ApiModelProperty(value = "{OK(\"成功\"), FAILED(\"失败\"), UNKNOWN(\"未明\"), CREATED(\"初创\")}")
    private String status;
    @ApiModelProperty(value = "错误码")
    private String code;
    @ApiModelProperty(value = "错误描述")
    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        //讲错误码转证通错误信息
        return errorMsgMap.getProperty(code);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "PayInfoResult{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + getDesc() + '\'' +
                '}';
    }

    private static Properties errorMsgMap = new Properties();
    private static Logger logger         = LoggerFactory.getLogger(PayInfoResult.class);
    /**
     * 使用static代码块，在系统启动时，从文件中初始.
     */
    static {
        try (InputStreamReader reader = new InputStreamReader(PayInfoResult.class.getClassLoader()
                .getResourceAsStream("errorMsg/pay_errro_code_msg.properties"), "UTF-8")) {
            errorMsgMap.load(reader);
        } catch (FileNotFoundException e) {
            logger.error("找不到文件:{}", e);
        } catch (IOException e) {
            logger.error("读取文件错误:{}", e);
        }
    }
}
