package com.swagger.service;

import com.swagger.domain.dto.IdDTO;
import com.swagger.domain.enums.GenderEnum;
import com.swagger.domain.vo.ResultVO;
import com.swagger.domain.vo.UserInfoVO;

import java.util.List;
public interface UserService {

    UserInfoVO queryUserInfo(IdDTO idDTO);

    List<UserInfoVO>  queryUserInfos(String name, GenderEnum genderEnum);
}
