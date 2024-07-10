package com.hanson.datapreheat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.datapreheat.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson
 * @date 2024/7/11 0:43
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
