package com.ii.iintelligence.api.controller.user;

import com.ii.biz.user.service.IUserDeviceService;
import com.ii.data.user.Entity.UserDeviceEntity;
import com.ii.data.user.query.UserDeviceQueryManagement;
import com.ii.domain.user.criteria.UserDeviceCriteria;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceType;
import com.ii.iintelligence.api.controller.assembler.common.DeviceAssembler;
import com.ii.iintelligence.api.controller.assembler.user.UserDeviceAssembler;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.common.DeviceListResult;
import com.ii.iintelligence.api.controller.vo.common.DeviceVo;
import com.ii.iintelligence.api.controller.vo.user.NewDeviceVo;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceCriteriaVo;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceListResult;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/userCenter")
@Api(value = "userCenter", description = "用户中心接口")
public class UserController {
    private final static Logger  logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserDeviceService userDevice;

    @Autowired
    private UserDeviceQueryManagement queryManagement;

    @ApiOperation(value = "创建设备", response = UserDeviceListResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/createDevice", method = POST)
    public DeviceListResult createDevice(@ApiParam(value = "创建设备", required = true)
                                                 @RequestBody NewDeviceVo newDeviceVo) {
        if(logger.isInfoEnabled())
            logger.info("创建设备 : {}", newDeviceVo.toString());
        DeviceListResult result = new DeviceListResult();
        if(StringUtils.isEmpty(newDeviceVo.getUid())){
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("uid为空");
            return result;
        }
        DeviceType deviceType = DeviceType.codeOf(newDeviceVo.getDeviceType());
        if(deviceType == DeviceType.Unknown){
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("设备类型不支持");
            return result;
        }
        List<Device> devices = userDevice.createUserDevice(newDeviceVo.getUid(), deviceType);
        List<DeviceVo> voList = DeviceAssembler.toVoList(devices);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        result.setVoList(voList);
        if(logger.isInfoEnabled())
            logger.info("创建设备 : {}", devices.toString());
        return result;
    }

    @ApiOperation(value = "查询用户的设备", response = UserDeviceListResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/queryDevice", method = POST)
    public UserDeviceListResult queryDevice(@ApiParam(value = "查询条件", required = true)
                                                @RequestBody UserDeviceCriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询用户的设备 : {}",criteriaVo.toString());
        }

        UserDeviceListResult result = new UserDeviceListResult();
        UserDeviceCriteria criteria = UserDeviceAssembler.toCriteria(criteriaVo);
        List<UserDeviceEntity> entities = queryManagement.queryUserDevice(criteria);
        List<UserDeviceVo> voList = UserDeviceAssembler.entity2VoList(entities);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        result.setVoList(voList);
        if(logger.isInfoEnabled()) {
            logger.info("查询用户的设备返回: {}", result.toString());
        }

        return result;
    }
}
