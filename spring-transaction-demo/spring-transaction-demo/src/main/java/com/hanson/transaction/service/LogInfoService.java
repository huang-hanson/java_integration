package com.hanson.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanson.transaction.domain.LogInfo;

/**
 * @author hanson
 * @date 2024/3/26 2:51
 */
public interface LogInfoService extends IService<LogInfo> {
    int add(LogInfo logInfo);
}
