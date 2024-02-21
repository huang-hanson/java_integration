package com.swagger.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    WOMAN(0,"女"),
    MAN(1,"男")
    ;
    private final int type;
    private final String desc;

    public static GenderEnum getGenderEnum(int type) {
        return Stream.of(values()).filter(value -> value.getType()==type).findFirst().orElse(null);
    }

    public static String getDescByType(int type) {
        GenderEnum genderEnum = getGenderEnum(type);
        if (genderEnum==null) return null;
        return genderEnum.getDesc();
    }
}
