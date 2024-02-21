package com.hanson.common.core.annotation;

import com.hanson.common.core.constant.DateFormatConstant;
import com.hanson.common.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Date;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateParamCheck.DateParamCheckValidator.class)
public @interface DateParamCheck {

    String message() default "时间参数格式错误";

    String formatter() default DateFormatConstant.YMD;

    boolean isEmpty() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class DateParamCheckValidator implements ConstraintValidator<DateParamCheck, Object> {

        private DateParamCheck constraintAnnotation;

        @Override
        public void initialize(DateParamCheck constraintAnnotation) {
            this.constraintAnnotation = constraintAnnotation;
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            if(constraintAnnotation.isEmpty() && StringUtils.isEmpty((String) o)){
                return true;
            }
            if (o instanceof String) {
                String time = (String) o;
                if (time.length() == constraintAnnotation.formatter().length()) {
                    Date date = DateUtil.formatDate((String) o, constraintAnnotation.formatter());
                    return null != date;
                }else{
                    return false;
                }
            }
            return false;
        }
    }
}
