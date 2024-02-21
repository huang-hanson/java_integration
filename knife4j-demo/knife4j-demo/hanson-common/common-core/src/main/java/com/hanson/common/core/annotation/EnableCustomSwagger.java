package com.hanson.common.core.annotation;

import com.hanson.common.core.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerConfig.class })
public @interface EnableCustomSwagger {
}

