package com.hanson.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.transaction.domain.LogInfo;

/**
 * @author hanson
 * @date 2024/3/26 2:52
 */
public interface LogInfoMapper extends BaseMapper<LogInfo> {
    int add(LogInfo logInfo);
}
