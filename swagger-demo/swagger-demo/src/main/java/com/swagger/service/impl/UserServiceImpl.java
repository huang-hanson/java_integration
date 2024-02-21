package com.swagger.service.impl;

import com.swagger.domain.dto.IdDTO;
import com.swagger.domain.entity.User;
import com.swagger.domain.enums.ExceptionEnum;
import com.swagger.domain.enums.GenderEnum;
import com.swagger.domain.vo.ResultVO;
import com.swagger.domain.vo.UserInfoVO;
import com.swagger.exceptions.CommonException;
import com.swagger.mapper.UserMapper;
import com.swagger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserInfoVO queryUserInfo(IdDTO idDTO) {
        User user = userMapper.selectByPrimaryKey(idDTO.getId());
        if (user == null){
            throw new CommonException(ExceptionEnum.NOT_FIND);
        }
        return UserInfoVO.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(GenderEnum.getDescByType(user.getGender()))
                .build();
    }

    @Override
    public List<UserInfoVO> queryUserInfos(String name, GenderEnum genderEnum) {
        Example example = Example.builder(User.class).build();
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)){
            // 模糊查询
            criteria.andLike("name","%"+name+"%");
        }
        criteria.andEqualTo("gender",genderEnum.getType());
        List<User> users = userMapper.selectByExample(example);
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        for (User user: users){
            userInfoVOS.add(UserInfoVO.builder()
                    .id(user.getId().toString())
                    .name(user.getName())
                    .birthday(user.getBirthday())
                    .gender(GenderEnum.getDescByType(user.getGender()))
                    .build());
        }
        return userInfoVOS;
    }
}
