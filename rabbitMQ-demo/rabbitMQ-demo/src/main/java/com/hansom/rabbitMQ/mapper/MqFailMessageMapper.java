package com.hansom.rabbitMQ.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hansom.rabbitMQ.entity.MqFailMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName MqFailMessageMapper
 * @Description TODO
 * @date 2024/7/22 14:12
 **/
@Mapper
public interface MqFailMessageMapper extends BaseMapper<MqFailMessage> {
}
