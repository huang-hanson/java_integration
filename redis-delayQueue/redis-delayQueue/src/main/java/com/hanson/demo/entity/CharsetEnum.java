package com.hanson.demo.entity;

import lombok.Getter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public enum CharsetEnum {
    /**
     * 常用编码
     */
    GBK(Charset.forName("GBK")),
    UTF8(StandardCharsets.UTF_8);

    private final Charset charset;

    CharsetEnum(Charset charset){
        this.charset = charset;
    }
}
