package com.ii.iintelligence.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.RequestVo;
import com.ii.iintelligence.api.controller.vo.GetResponseResult;
import com.ii.iintelligence.api.controller.vo.PostResponseResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trading")
@Api(value = "example", description = "示例相关接口")
public class ExampleApiController {
    private final static Logger  logger = LoggerFactory.getLogger(ExampleApiController.class);

    @ApiOperation(value = "查询支付信息", response = GetResponseResult.class, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "tradingId", dataType = "string", required = true, value = "交易单ID")})
    @ResponseBody
    @RequestMapping(value = "/payInfo", method = GET)
    public GetResponseResult payInfo(String tradingId){
        if(logger.isInfoEnabled()) {
            logger.info("查询支付信息， tradingId : {}",tradingId);
        }
        GetResponseResult payInfoResult = new GetResponseResult();

        if(logger.isInfoEnabled()) {
            logger.info("查询支付信息返回: {}", payInfoResult.toString());
        }

        return payInfoResult;
    }


    @ApiOperation(value = "认购", response = PostResponseResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/subscribe", method = POST)
    public PostResponseResult subscribe(@ApiParam(value = "认购", required = true) @RequestBody RequestVo requestVo) {
        if(logger.isInfoEnabled())
            logger.info("认购请求: "+ requestVo.toString());
        PostResponseResult result = new PostResponseResult();

        if(logger.isInfoEnabled() && result.getResultCode().equals(WebConstants.RESULT_SUCCESS_CODE))
            logger.info("===== 认购请求成功 =====");
        else if(result.getResultCode().equals(WebConstants.RESULT_FAIL_CODE))
            logger.warn("!--! 认购请求失败，返回: {}", result.getMessage());
        return result;
    }
}
