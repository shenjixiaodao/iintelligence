package com.ii.iintelligence.api.controller.common;

import com.ii.biz.common.service.BaseDeviceService;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.common.DeviceResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/common")
@Api(value = "user", description = "通用用户控制相关接口")
public class CommonUserController {
    private final static Logger  logger = LoggerFactory.getLogger(CommonUserController.class);

    @Autowired
    private BaseDeviceService deviceService;

    @ApiOperation(value = "获取设备ID", response = DeviceResult.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "deviceType", dataType = "string", required = true, value = "设备类型{}"),
            @ApiImplicitParam(paramType="query", name = "uid", dataType = "string", required = true, value = "交易单ID")
    })
    @ResponseBody
    @RequestMapping(value = "/fetchDeviceId", method = GET)
    public DeviceResult fetchDeviceId(String deviceType, String uid){
        if(logger.isInfoEnabled()) {
            logger.info("获取设备ID，[deviceType = {}, uid = {}]",deviceType, uid);
        }
        DeviceResult deviceResult = new DeviceResult();
        if(StringUtils.isEmpty(uid)){
            deviceResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
            deviceResult.setMessage("uid为空");
            if(logger.isInfoEnabled()) {
                logger.info("uid为空");
            }
            return deviceResult;
        }
        DeviceType type = DeviceType.codeOf(deviceType);
        if(type == DeviceType.Unknown){
            deviceResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
            deviceResult.setMessage(deviceType+"设备类型不支持");
            if(logger.isInfoEnabled()) {
                logger.info("{}设备类型不支持", deviceType);
            }
            return deviceResult;
        }
        DeviceId deviceId = deviceService.fetchDeviceId(type, uid);
        deviceResult.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        deviceResult.setDeviceId(deviceId.toString());
        if(logger.isInfoEnabled()) {
            logger.info("获取设备ID返回 : {}", deviceResult.toString());
        }
        return deviceResult;
    }

}
