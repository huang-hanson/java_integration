package com.hanson.server.system.controller;

import com.hanson.common.core.domain.dto.Result;
import com.hanson.server.system.domain.dto.TestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = "【测试-方法】")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class TestController {

    @GetMapping("/get/{id}")
    @ApiOperation(value = "get-测试方法")
    public Result<TestDto> testGet(@PathVariable("id") Long id) {
        System.out.println("Get 方法测试：" + id);
        // 根据 id 查询
        TestDto dto = new TestDto();
        dto.setName("名称");
        dto.setValue("黄汉升");
        return Result.success(dto);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "del-测试方法")
    public Result<String> testDel(@PathVariable("id") Long id) {
        System.out.println("Del 方法测试：" + id);
        return Result.success();
    }

    @PostMapping("/post")
    @ApiOperation(value = "post-测试方法")
    public Result<String> testPost(@RequestBody TestDto req) {
        System.out.println("Post 方法测试：" + req.toString());
        return Result.success();
    }

    @PutMapping("/put")
    @ApiOperation(value = "put-测试方法")
    public Result<String> testPut(@RequestBody TestDto req) {
        System.out.println("Put 方法测试：" + req.toString());
        return Result.success();
    }
}
