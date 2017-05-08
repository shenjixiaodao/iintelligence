package com.ii.iintelligence.api.controller.switchgear;

import com.ect.common.error.Result;
import com.ii.biz.switchgear.AyncContinuation.UserAyncContinuationService;
import com.ii.biz.switchgear.service.IUserSwitchService;
import com.ii.domain.switchgear.handler.AbstractUserSwitchesHandler;
import com.ii.domain.switchgear.handler.UserSwitchHandler;
import com.ii.domain.switchgear.GroupsSwitch;
import com.ii.domain.switchgear.Switch;
import com.ii.iintelligence.api.controller.assembler.switchgear.SwitchAssembler;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.switchgear.*;
import io.swagger.annotations.*;
import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/switch")
@Api(value = "user", description = "开关设备用户控制相关接口")
public class UserSwitchController {
    private final static Logger  logger = LoggerFactory.getLogger(UserSwitchController.class);

    @Autowired
    private UserAyncContinuationService syncContinuationService;

    @Autowired
    private IUserSwitchService userSwitchService;

    @ApiOperation(value = "发布状态事件", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/publishStatusEvent", method = POST)
    public SwitchResult publishStatusEvent(
            @ApiParam(value = "开关", required = true) @RequestBody(required = false) final SwitchVo switchVo,
                                              HttpServletResponse response, HttpServletRequest request){
        if(logger.isInfoEnabled()) {
            logger.info("改变开关状态， switchVo : {}",switchVo.toString());
        }
        // if we need to get asynchronous results
        Result<Switch> result = (Result) request.getAttribute("result");
        if (result == null)
        {
            final Continuation continuation = ContinuationSupport.getContinuation(request);
            // if this is not a timeout
            if (continuation.isExpired())
            {
                //todo 超时处理
                return null;
            }
            // suspend the request
            continuation.suspend(); // always suspend before registration
            syncContinuationService.registerStatusEventHandler(new UserSwitchHandler() {
                @Override
                public Switch getSwitch() {
                    return SwitchAssembler.voToSwitch(switchVo);
                }
                @Override
                public void resultReadyEvent(Result result) {
                    continuation.setAttribute("result",result);
                    continuation.resume();
                }
            });
            return null; // or continuation.undispatch();
        }

        SwitchResult switchResult = SwitchAssembler.switchToWebResult(result);

        if(logger.isInfoEnabled()) {
            logger.info("开关等待状态改变: {}", switchResult.toString());
        }

        return switchResult;
    }

    @ApiOperation(value = "请求改变分组开关状态", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/changeSwitchesStatus", method = POST)
    public SwitchListResult changeSwitchesStatus(
            @ApiParam(value = "开关", required = true) @RequestBody(required = false) final List<SwitchVo> switchVos,
                                             HttpServletResponse response, HttpServletRequest request){
        if(logger.isInfoEnabled()) {
            logger.info("改变开关状态， switchVo : {}",switchVos.toString());
        }
        // if we need to get asynchronous results
        Result<List<Switch>> result = (Result) request.getAttribute("result");
        if (result == null)
        {
            final Continuation continuation = ContinuationSupport.getContinuation(request);
            // if this is not a timeout
            if (continuation.isExpired())
            {
                //todo 超时处理
                return null;
            }
            // suspend the request
            continuation.suspend(); // always suspend before registration
            syncContinuationService.registerStatusEventHandler(
                    new AbstractUserSwitchesHandler(SwitchAssembler.vosToSwitches(switchVos)) {
                @Override
                public void doResultReadyEvent(Result result) {
                    continuation.setAttribute("result",result);
                    continuation.resume();
                }
            });
            return null; // or continuation.undispatch();
        }

        SwitchListResult switchResult = SwitchAssembler.switchesToWebListResult(result);

        if(logger.isInfoEnabled()) {
            logger.info("开关等待状态改变: {}", switchResult.toString());
        }

        return switchResult;
    }

    @ApiOperation(value = "查询在线设备信息", response = SwitchListResult.class, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "uid", dataType = "string", required = true, value = "用户ID")})
    @ResponseBody
    @RequestMapping(value = "/getOnlineSwitches", method = GET)
    public GroupsSwitchResult getOnlineSwitches(String uid){
        if(logger.isInfoEnabled()) {
            logger.info("查询设备信息， uid : {}",uid);
        }

        GroupsSwitchResult groupsSwitchResult = new GroupsSwitchResult();
        if(StringUtils.isEmpty(uid)){
            groupsSwitchResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
            groupsSwitchResult.setMessage("uid为空");
            return groupsSwitchResult;
        }

        GroupsSwitch groups = userSwitchService.findGroupSwitch(uid);
        groupsSwitchResult.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        List<GroupSwitchVo> groupsVo = SwitchAssembler.groupsSwitchToWebResult(groups);
        groupsSwitchResult.setGroupSwitchVos(groupsVo);

        if(logger.isInfoEnabled()) {
            logger.info("查询分组设备信息返回 : {}", groupsSwitchResult.toString());
        }

        return groupsSwitchResult;
    }

}
