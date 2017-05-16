package com.ii.iintelligence.api.controller.switchgear;

import com.ect.common.error.Result;
import com.ii.biz.switchgear.AyncContinuation.SwitchAyncContinuationService;
import com.ii.domain.switchgear.handler.AbstractSwitchesHandler;
import com.ii.domain.switchgear.handler.SwitchHandler;
import com.ii.domain.switchgear.Switch;
import com.ii.iintelligence.api.controller.assembler.switchgear.SwitchAssembler;
import com.ii.iintelligence.api.controller.vo.switchgear.GroupSwitchVo;
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
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/switch")
@Api(value = "switch", description = "开关设备相关接口")
public class SwitchController {
    private final static Logger  logger = LoggerFactory.getLogger(SwitchController.class);

    @Autowired
    private SwitchAyncContinuationService syncContinuationService;

    @ApiOperation(value = "订阅状态事件", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/subscribeStatusEvent", method = POST)
    public SwitchResult subscribeStatusEvent(
            @ApiParam(value = "开关", required = true) @RequestBody(required = false) final SwitchVo switchVo,
                                              HttpServletResponse response, HttpServletRequest request){
        if(logger.isInfoEnabled()) {
            logger.info("开关等待状态改变， switchVo : {}",switchVo);
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

            syncContinuationService.registerStatusEventHandler(new SwitchHandler()
            {
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

    @ApiOperation(value = "订阅分组开关状态改变", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/subscribeSwitchesStatusChanged", method = POST)
    public SwitchListResult subscribeSwitchesStatusChanged(
            @ApiParam(value = "开关", required = true) @RequestBody(required = false) final GroupSwitchVo groupSwitchVo,
                                                 HttpServletResponse response, HttpServletRequest request){
        if(logger.isInfoEnabled()) {
            logger.info("分组开关等待状态改变，  {}",groupSwitchVo);
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
            // suspend the request SwitchAssembler.vosToSwitches(switchVos)
            continuation.suspend(); // always suspend before registration
            syncContinuationService.registerStatusEventHandler(
                    new AbstractSwitchesHandler(SwitchAssembler.voToGroupSwitch(groupSwitchVo)) {
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
            logger.info("分组开关等待状态改变: {}", switchResult.toString());
        }

        return switchResult;
    }

}
