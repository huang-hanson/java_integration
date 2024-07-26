package com.hanson.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.hanson.demo.entity.CharsetEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BaseRedisTemplateConfig
 * @Description TODO
 * @date 2024/7/23 18:47
 **/
@Configuration
@ComponentScan(basePackageClasses = BaseRedisLettuceConfig.class)
public class BaseRedisTemplateConfig {
    /**
     * jackson序列化的redis template
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     * @author linton.cao
     * @date 2022/1/20 13:39
     */
    @Bean(name = { "objectRedisTemplate", "redisTemplate" })
    @ConditionalOnMissingBean(name = { "objectRedisTemplate", "redisTemplate" })
    @Primary
    public RedisTemplate<String, Object> objectRedisTemplate(@Autowired @Qualifier("lettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory) {
        return getRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * redisTemplate
     * key使用string方式序列化
     * value使用jackson方式序列化
     * 用于操作redis中以json方式存储的对象
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        //设置redis反序列化时忽略未知属性,该配置默认为true,这里设置为false
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //key hashmap序列化
        template.setHashKeySerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * stringRedisTemplate专用于获取string类型数据
     * 使用utf-8编码格式获取redis中的string数据，这样中文不会出现乱码情况
     */
    @Bean(name = { "stringUtf8RedisTemplate", "stringRedisTemplate" })
    @ConditionalOnMissingBean(name = { "stringUtf8RedisTemplate", "stringRedisTemplate" })
    @Primary
    public StringRedisTemplate stringUtf8RedisTemplate(@Autowired @Qualifier("lettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory){
        return getStringUtf8RedisTemplate(lettuceConnectionFactory);
    }

    /**
     * stringRedisTemplate专用于获取string类型数据
     * 使用utf-8编码格式获取redis中的string数据，这样中文不会出现乱码情况
     */
    public static StringRedisTemplate getStringUtf8RedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer(CharsetEnum.UTF8.getCharset());
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        return template;
    }

    /**
     * 兼容php老数据的gbk string的redis template，非必要勿用
     * @return org.springframework.data.redis.core.StringRedisTemplate
     * @author linton.cao
     * @date 2022/1/20 13:41
     */
    @Bean("stringGbkRedisTemplate")
    @ConditionalOnMissingBean(name = "stringGbkRedisTemplate")
    public StringRedisTemplate stringGbkRedisTemplate(@Autowired @Qualifier("lettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory){
        return getStringGbkRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * stringRedisTemplate专用于获取string类型数据
     * 使用gb2312编码格式获取redis中的string数据，这样中文不会出现乱码情况
     */
    public static StringRedisTemplate getStringGbkRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer(CharsetEnum.GBK.getCharset());
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        return template;
    }
}