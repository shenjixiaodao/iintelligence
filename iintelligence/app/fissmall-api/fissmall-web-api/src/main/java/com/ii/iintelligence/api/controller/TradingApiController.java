package com.ii.iintelligence.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import com.ii.iintelligence.api.controller.constatns.FissWebConstants;
import com.ii.iintelligence.api.controller.vo.NewSubscribeVo;
import com.ii.iintelligence.api.controller.vo.PayInfoResult;
import com.ii.iintelligence.api.controller.vo.TradingResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trading")
@Api(value = "trading", description = "交易相关接口")
public class TradingApiController {
    private final static Logger  logger = LoggerFactory.getLogger(TradingApiController.class);

    @ApiOperation(value = "查询支付信息", response = PayInfoResult.class, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "tradingId", dataType = "string", required = true, value = "交易单ID")})
    @ResponseBody
    @RequestMapping(value = "/payInfo", method = GET)
    public PayInfoResult payInfo(String tradingId){
        if(logger.isInfoEnabled()) {
            logger.info("查询支付信息， tradingId : {}",tradingId);
        }
        PayInfoResult payInfoResult = new PayInfoResult();

        if(logger.isInfoEnabled()) {
            logger.info("查询支付信息返回: {}", payInfoResult.toString());
        }

        return payInfoResult;
    }


    @ApiOperation(value = "认购", response = TradingResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/subscribe", method = POST)
    public TradingResult subscribe(@ApiParam(value = "认购", required = true) @RequestBody NewSubscribeVo newSubscribeVo) {
        if(logger.isInfoEnabled())
            logger.info("认购请求: "+newSubscribeVo.toString());
        TradingResult result = new TradingResult();

        if(logger.isInfoEnabled() && result.getResultCode().equals(FissWebConstants.RESULT_SUCCESS_CODE))
            logger.info("===== 认购请求成功 =====");
        else if(result.getResultCode().equals(FissWebConstants.RESULT_FAIL_CODE))
            logger.warn("!--! 认购请求失败，返回: {}", result.getMessage());
        return result;
    }
}
