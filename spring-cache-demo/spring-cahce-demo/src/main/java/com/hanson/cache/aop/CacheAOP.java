package com.hanson.cache.aop;

import com.hanson.cache.config.CacheAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/3/18 17:35
 */
@Slf4j
@Aspect
@Component
public class CacheAOP {

    /**
     * 自动注入RedisTemplate
     */
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 定义切入点：返回值任意，service包下所有的方法，参数值任意
     */
    @Pointcut("execution(* com.hanson.cache.service..*(..))")
    public void pointCut() {

    }

    /**
     * 定义切面：
     * 1.获得方法的类对象-->通过Class对象获得方法的Method对象
     * 2.获得方法的签名-->通过签名拿到方法的名称，参数类型-->获得方法的Method对象
     * 3.获得方法的参数的值-->作为缓存数据读写的key值
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object cacheOption(ProceedingJoinPoint pjp) throws Throwable {
        // 拿到该方法的类对象
        Class<?> cls = pjp.getTarget().getClass();
        // 拿到方法签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 拿到方法名称
        String methodName = signature.getName();
        // 拿到参数类型
        Class[] parameterTypes = signature.getParameterTypes();
        // 获取方法类对象
        Method method = cls.getMethod(methodName, parameterTypes);

        if (!method.isAnnotationPresent(CacheAnnotation.class)) {
            Object proceed = pjp.proceed();
            return proceed;
        }

        //如果被自定义的注解标注,调用getRedisKey,获得key值
        String redisKey = getRedisKey(signature, pjp, cls, methodName);
        log.debug("最后的RedisKey值是：{}", redisKey);

        //通过redisTemplate查询redis对应key的值
        Object result = redisTemplate.opsForValue().get(redisKey);

        //如果不为空：直接返回这个值，不执行调用的方法
        if (result != null){
            log.debug("从redis中拿到的数据：{}",result);
            return result;
        }

        // 执行方法 获取返回值
        Object proceed = pjp.proceed();
        log.debug("从数据库中拿到的数据：{}",proceed);

        //将返回值存入redis当中作为缓存数据，过期时间为一分钟
        redisTemplate.opsForValue().set(redisKey,proceed,1L, TimeUnit.MINUTES);
        return proceed;

    }

    /**
     * 拿到类名，方法名，参数名，参数值；然后拼接成一个字符串，类似于：BrandServiceImpl:getById:id{6}
     * 这个字符串作为缓存数据的key值
     *
     * @param signature
     * @param pjp
     * @param cls
     * @param methodName
     * @return 返回缓存数据的key值
     */
    public String getRedisKey(MethodSignature signature, ProceedingJoinPoint pjp, Class cls, String methodName) {
        // 获取传进来的参数名称
        String[] parameterNames = signature.getParameterNames();
        log.debug("参数名称：{}", parameterNames);
        // 获取传进来的参数值
        Object[] args = pjp.getArgs();
        log.debug("args的值是：{}", args);
        // 获取类名
        String clsName = cls.getName();
        log.info("className的值是：{}", clsName);
        StringBuilder redisKey = new StringBuilder(clsName.substring(clsName.lastIndexOf(".") + 1, clsName.length()));
        redisKey.append(":" + methodName);
        for (int i = 0; i < parameterNames.length; i++) {
            redisKey.append(":");
            redisKey.append(parameterNames[i]);
            redisKey.append("{");
            redisKey.append(args[i]);
            redisKey.append("}");
        }
        if (parameterNames.length == 0) {
            redisKey.append(":NoArgs");
        }
        return redisKey.toString();

    }
}
