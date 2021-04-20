package top.chang888.common.utils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * @author changyw
 * @date 2021/4/16
 */
@Slf4j
public class JwtsUtils {

    private JwtsUtils() {
    }

    /**
     * token 过期时间: 10天
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final int CALENDAR_FIELD = Calendar.MINUTE;
    public static final int CALENDAR_INTERVAL = 60;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param username 登录成功后用户username, 参数username不可传空
     */
    public static String sign(String username, String secret) {

        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);

        // build token
        // param backups {iss:Service, aud:APP}
        // header
        String token = Jwts.builder()
                                .setHeaderParam("alg", "HS256")
                                .setHeaderParam("typ", "JWT")
                //                .setClaims()
                                // payload
                                .setSubject(username)
                                // sign time
                                .setIssuedAt(new Date())
                                // expire time
                                .setExpiration(nowTime.getTime())
                                // signature;
                                .signWith(SignatureAlgorithm.HS256, secret)
                                .compact();
        return "Bearer " + token;
    }

    /**
     * 验证token
     * @param token 用户token
     * @param secret 盐
     * @return true / false
     */
    public static boolean verifyToken(String token, String secret) {
        log.info("verifyToken - {}, {}", token, secret);
        try {
            parseToken(token, secret);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 检查token的格式是否正确
     * @param token 用户token
     * @return true / false
     */
    public static boolean verifyTokenFormat(String token) {
        if (StrUtil.isEmpty(token)) {
            return false;
        }
        String[] tokens = token.split("\\.");
        return tokens.length == 3;
    }

    /**
     * 直接通过token 获取username
     * @param token 用户 token 信息
     * @return Str username
     */
    public static String getUsername(String token) throws JSONException {
        if (!verifyTokenFormat(token)) {
            return "";
        }
        String[] tokens = token.split("\\.");
        String s = Base64Decoder.decodeStr(tokens[1]);
        return new JSONObject(s).getString("sub");
    }

    /**
     * 解析JWT 获取Claims
     * @param token 用户 token 信息
     * @return Claims
     */
    public static Claims parseToken(String token, String secret) throws ExpiredJwtException {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }


    /**
     * token还有多少时间过期
     * @param token 用户token
     * @param secret 盐
     * @return long
     */
    public static long surExpires(String token, String secret) {
        Claims claims = parseToken(token, secret);
        return (claims.getExpiration().getTime() - claims.getIssuedAt().getTime()) / 1000;
    }
}
