package com.yy.springcloud.utils;


import com.yy.springcloud.config.AudienceConfig;
import com.yy.springcloud.exception.CustomException;
import com.yy.springcloud.model.LumiApiResEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.*;

/**
 * JwtUtil
 *
 * @author tianyi.wei
 */
@Slf4j
public class JwtCreator {

    /**
     * 构建jwt
     *
     * @param userId         用户id
     * @param username       用户名
     * @param role           用户角色
     * @param audienceConfig audienceConfig
     * @return jwt
     */
    public static String createJwt(String userId, String username, Integer role, AudienceConfig audienceConfig) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audienceConfig.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", userId)
                    // 代表这个JWT的主体，即它的所有人
                    .setSubject(username)
                    // 代表这个JWT的签发主体
                    .setIssuer(audienceConfig.getClientId())
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(audienceConfig.getName())
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int ttlMillis = audienceConfig.getExpiresSecond() * 1000;
            if (ttlMillis >= 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                // 是一个时间戳，代表这个JWT的过期时间
                builder.setExpiration(exp)
                        // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
                        .setNotBefore(now);
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new CustomException(LumiApiResEnum.AUTH_ERR, "sig error");
        }
    }

    public static Claims parseJwt(String token, String base64Security) throws CustomException {
        if (Strings.isBlank(token)) {
            return null;
        }
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException token error:{}",e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Exception token error:{}",e.getMessage());
        }
        return null;
    }


}
