package com.ii.iintelligence.api.controller.switchgear;

import com.ect.common.error.Result;
import com.ii.biz.AyncContinuation.UserAyncContinuationService;
import com.ii.domain.handler.UserSwitchHandler;
import com.ii.domain.switchgear.Switch;
import com.ii.iintelligence.api.controller.assembler.switchgear.SwitchAssembler;
import com.ii.iintelligence.api.controller.vo.switchgear.SwitchListResult;
import com.ii.iintelligence.api.controller.vo.switchgear.SwitchResult;
import com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo;
import io.swagger.annotations.*;
import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/switch")
@Api(value = "user", description = "开关设备用户控制相关接口")
public class SwitchUserController {
    private final static Logger  logger = LoggerFactory.getLogger(SwitchUserController.class);

    @Autowired
    private UserAyncContinuationService syncContinuationService;

    @ApiOperation(value = "请求状态改变", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/changeState", method = POST)
    public SwitchResult waitStateChanged(@ApiParam(value = "开关", required = true) @RequestBody final SwitchVo switchVo,
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
            syncContinuationService.registerStateCommandHandler(new UserSwitchHandler() {
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

    @ApiOperation(value = "查询设备信息", response = SwitchListResult.class, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "uid", dataType = "string", required = true, value = "交易单ID")})
    @ResponseBody
    @RequestMapping(value = "/getSwitches", method = GET)
    public SwitchListResult getSwitches(String uid){
        if(logger.isInfoEnabled()) {
            logger.info("查询设备信息， uid : {}",uid);
        }

        SwitchListResult switchListResult = new SwitchListResult();

        if(logger.isInfoEnabled()) {
            logger.info("查询设备信息返回 : {}", switchListResult.toString());
        }

        return switchListResult;
    }

}
