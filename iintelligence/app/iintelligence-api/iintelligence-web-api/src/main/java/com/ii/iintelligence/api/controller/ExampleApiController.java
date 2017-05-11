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
@RequestMapping(value = "/example")
@Api(value = "example", description = "示例相关接口")
public class ExampleApiController {
    private final static Logger  logger = LoggerFactory.getLogger(ExampleApiController.class);

    @ApiOperation(value = "get方法", response = GetResponseResult.class, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id", dataType = "string", required = true, value = "ID")})
    @ResponseBody
    @RequestMapping(value = "/get", method = GET)
    public GetResponseResult get(String id){
        if(logger.isInfoEnabled()) {
            logger.info("get : {}",id);
        }
        GetResponseResult getResponseResult = new GetResponseResult();

        if(logger.isInfoEnabled()) {
            logger.info("get {}", getResponseResult.toString());
        }

        return getResponseResult;
    }


    @ApiOperation(value = "post方法", response = PostResponseResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/post", method = POST)
    public PostResponseResult post(@ApiParam(value = "请求Vo", required = true) @RequestBody RequestVo requestVo) {
        if(logger.isInfoEnabled())
            logger.info("post请求: "+ requestVo.toString());
        PostResponseResult result = new PostResponseResult();

        return result;
    }
}
