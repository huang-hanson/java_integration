package com.hanson.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.transaction.domain.LogInfo;
import com.hanson.transaction.mapper.LogInfoMapper;
import com.hanson.transaction.service.LogInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author hanson
 * @date 2024/3/26 2:51
 */
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {

    @Resource
    private LogInfoMapper logInfoMapper;

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int add(LogInfo logInfo) {
        int result = logInfoMapper.add(logInfo);
        System.out.println("添加日志结果：" + result);
        int number = 10/0;
        return result;
    }
}
