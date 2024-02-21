package com.swagger.controller;

import com.swagger.domain.dto.IdDTO;
import com.swagger.domain.enums.GenderEnum;
import com.swagger.domain.vo.ResultVO;
import com.swagger.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/query-user-info")
    @ApiModelProperty(value = "根据id查看用户详情")
    public ResultVO queryUserInfo(@RequestBody @Validated IdDTO idDTO){
        return ResultVO.success(userService.queryUserInfo(idDTO));
    }

    @PostMapping(value = "/query-user-infos")
    @ApiOperation(value = "条件查询用户信息")
    public ResultVO queryUserInfos(
            // name 用户名称 不必填
            @ApiParam(value = "用户名称",required = false) @RequestParam(required = false) String name,
            // gender 用户性别  必填
            @ApiParam(value = "用户性别",required = true) @RequestParam(required = true)GenderEnum genderEnum
            ){
        return ResultVO.success(userService.queryUserInfos(name,genderEnum));
    }

    @GetMapping(value = "/get-token")
    @ApiOperation(value = "获取请求头中的token信息")
    public void getToken(
            @RequestHeader(value = "token",required = false) String token
    ){
        // 直接获取 token 信息
        System.out.println("token = " + token);

        // 通过代码获取
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String header = request.getHeader("token");
            System.err.println("header = " + header);
        }
    }
}
