package com.danny.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.danny.commons.utils.SystemConfig;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "/goods" })
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @ApiOperation(value="添加商品信息", notes="添加新增商品的详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping({ "/user_info" })
    @ResponseBody
    public String greeting(HttpServletRequest request) {
        String cookie = request.getHeader("cookie");
        String referer = request.getHeader("referer");
        logger.info("----cookie=" + cookie);
        logger.info("----referer=" + referer);
//        logger.info("------------url=" + AiermuHttpUrlUtil.makeGetUserInfoUrl());
//        String userInfo = HttpClientUtil.doGet4Https(AiermuHttpUrlUtil.makeGetUserInfoUrl(), cookie, referer);
//        logger.info("------------userInfo=" + userInfo);
        return SystemConfig.getString("redis.ip");
    }
}