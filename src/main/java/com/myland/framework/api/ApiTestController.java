package com.myland.framework.api;

import com.myland.framework.utils.page.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myland.framework.entity.UserEntity;
import com.myland.framework.utils.annotation.IgnoreAuth;
import com.myland.framework.utils.annotation.LoginUser;

/**
 * API测试接口
 *
 */
@RestController
@RequestMapping("/api")
@Api("测试接口")
public class ApiTestController {

    /**
     * 获取用户信息
     */
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
    public ResponseMessage userInfo(@LoginUser UserEntity user){
        return ResponseMessage.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @IgnoreAuth
    @GetMapping("notToken")
    @ApiOperation(value = "忽略Token验证测试")
    public ResponseMessage notToken(){
        return ResponseMessage.ok().put("msg", "无需token也能访问。。。");
    }
}
