package com.ii.iintelligence.api.controller.switchgear;

import com.ect.common.error.Result;
import com.ii.biz.switchgear.AyncContinuation.UserAyncContinuationService;
import com.ii.domain.switchgear.event.SwitchEvent;
import com.ii.domain.switchgear.handler.UserSwitchEventHandler;
import com.ii.iintelligence.api.controller.assembler.switchgear.SwitchAssembler;
import com.ii.iintelligence.api.controller.vo.switchgear.*;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/test")
@Api(value = "test", description = "test")
public class UserSwitchController {
    private final static Logger  logger = LoggerFactory.getLogger(UserSwitchController.class);

    @Autowired
    private UserAyncContinuationService syncContinuationService;

    @ApiOperation(value = "订阅", response = SwitchResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/subscribe", method = POST)
    public SwitchEventResult subscribe(
            @ApiParam(value = "开关", required = true) @RequestBody(required = false) final SwitchEventVo eventVo,
                                              HttpServletResponse response, HttpServletRequest request){
        if(logger.isInfoEnabled()) {
            logger.info("改变开关状态， eventVo : {}", eventVo);
        }
        // if we need to get asynchronous results
        Result<SwitchEvent> result = (Result) request.getAttribute("result");
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
            syncContinuationService.registerStatusEventHandler(new UserSwitchEventHandler() {
                @Override
                public SwitchEvent getEvent() {
                    return SwitchAssembler.toSwitchEvent(eventVo);
                }
                @Override
                public void resultReadyEvent(Result result) {
                    continuation.setAttribute("result",result);
                    continuation.resume();
                }
            });
            return null; // or continuation.undispatch();
        }

        SwitchEventResult switchResult = SwitchAssembler.toSwitchEventResult(result);

        if(logger.isInfoEnabled()) {
            logger.info("开关等待状态改变: {}", switchResult.toString());
        }

        return switchResult;
    }

}
