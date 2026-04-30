package org.gromila.shopapp.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.gromila.shopapp.annotation.CacheEvict;
import org.gromila.shopapp.annotation.CachePut;
import org.gromila.shopapp.annotation.Cached;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Aspect
@Component
@RequiredArgsConstructor
public class CacheAspect {
    private final RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(cached)")
    public void cachedPointcut(Cached cached) {
    }

    @Pointcut("@annotation(cachePut)")
    public void cachePutPointcut(CachePut cachePut) {
    }

    @Pointcut("@annotation(cacheEvict)")
    public void cacheEvictPointcut(CacheEvict cacheEvict) {
    }

    @Around("cachedPointcut(cached)")
    public Object handleCached(ProceedingJoinPoint joinPoint, Cached cached) throws Throwable {
        String prefix = cached.prefix();
        String key = cached.key();
        String evaluatedKey = evaluateKey(joinPoint, key);
        String redisKey = prefix + ":" + evaluatedKey;

        Object cachedValue = redisTemplate.opsForValue().get(redisKey);
        if (cachedValue != null) return cachedValue;

        Object result = joinPoint.proceed();
        if (result != null) {
            redisTemplate.opsForValue().set(redisKey, result, Duration.ofSeconds(cached.ttl()));
        }
        return result;
    }

    @Around("cachePutPointcut(cachePut)")
    public Object handleCachePut(ProceedingJoinPoint joinPoint, CachePut cachePut) throws Throwable {
        String prefix = cachePut.prefix();
        String key = cachePut.key();
        String evaluatedKey = evaluateKey(joinPoint, key);
        String redisKey = prefix + ":" + evaluatedKey;

        Object result = joinPoint.proceed();
        if (result != null) {
            redisTemplate.opsForValue().set(redisKey, result, Duration.ofSeconds(cachePut.ttl()));
        }
        return result;
    }

    @Before("cacheEvictPointcut(cacheEvict)")
    public void handleCacheEvict(JoinPoint joinPoint, CacheEvict cacheEvict) {
        String prefix = cacheEvict.prefix();
        boolean allEntries = cacheEvict.allEntries();

        if (allEntries) {
            Set<String> keys = redisTemplate.keys(prefix + ":*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
        } else {
            String keyExpression = cacheEvict.key();
            String evaluatedKey = evaluateKey(joinPoint, keyExpression);
            String redisKey = prefix + ":" + evaluatedKey;
            redisTemplate.delete(redisKey);
        }
    }

    private String evaluateKey(JoinPoint joinPoint, String keyExpression) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        ExpressionParser parser = new SpelExpressionParser();
        Object value = parser.parseExpression(keyExpression).getValue(context);
        return String.valueOf(value);
    }
}
