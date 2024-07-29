package com.hansom.rabbitMQ.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hansom.rabbitMQ.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName StudentMapper
 * @Description TODO
 * @date 2024/7/22 14:38
 **/
@Mapper
public interface StudentMapper extends BaseMapper<StudentInfo> {
}
