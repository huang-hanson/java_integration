package com.swagger.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVO {
    private String id;//用户id
    private String name;//用户名称
    private String birthday;//用户生日
    private String gender;//用户性别
}
