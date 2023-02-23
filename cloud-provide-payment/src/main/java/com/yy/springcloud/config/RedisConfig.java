package com.yy.springcloud.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * Date: 2020/4/7
 */
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@EnableCaching
public class RedisConfig  {

    /**
     *
     * CacheAutoConfiguration 会导入 RedisCacheConfiguration;
     * 会自动装配缓存管理器 RedisCacheManager;
     * 启动类添加 @EnableCaching
     * 方法添加Cacheable(value={“category”})
     *
     *
     * 配置文件的配置没有用上
     * 1. 原来和配置文件绑定的配置类为：@ConfigurationProperties(prefix = "spring.cache")
     * public class CacheProperties
     * <p>
     * 2. 要让他生效，要加上直接绑定类CacheProperties @EnableConfigurationProperties(CacheProperties.class)
     *
     * 1、第一种使用方式
     * @Autowired
     * CacheProperties cacheProperties
     * 2、因为配置都在容器中了，可以直接作为参数传过来
     *   public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties)
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // config = config.entryTtl();
        //JSON序列化数据 key value
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        //将配置文件中所有的配置都生效
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        // 指定key前缀
        // 2.3之前使用prefixKeysWith 之后使用 prefixCacheNameWith or computePrefixWith
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        //是否缓存null
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        //是否使用键前缀
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }
}
