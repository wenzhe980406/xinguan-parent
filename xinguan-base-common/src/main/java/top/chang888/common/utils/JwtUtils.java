package top.chang888.common.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import top.chang888.common.handler.BusinessException;
import top.chang888.common.response.ResultCode;

import java.util.*;

/**
 * @author changyw
 * @date 2021/4/15
 */
public class JwtUtils {

    private JwtUtils() {
    }

    /**
     * 过期时间6小时
     */
    private static final long EXPIRE_TIME = 6*60*60*1000;

    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoadsafa
     */
    public static final String SECRET = "JKKLJOoadsafa";

    /**
     * token 过期时间: 10天
     */
    public static final int CALENDAR_FIELD = Calendar.DATE;
    public static final int CALENDAR_INTERVAL = 10;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param username 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String sign(String username) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
                // header
        return JWT.create().withHeader(map)
                // payload
                .withClaim("iss", "Service")
                .withClaim("aud", "APP")
                .withClaim("username", username)
                // sign time
                .withIssuedAt(iatDate)
                // expire time
                .withExpiresAt(expiresDate)
                // signature;
                .sign(Algorithm.HMAC256(SECRET));
    }


    /**
     * 解密Token
     * @param token 用户token
     * @return map
     */
    public static DecodedJWT verifyToken(String token) {
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // token 校验失败, 抛出Token验证非法异常
            throw new BusinessException(ResultCode.USER_TOKEN_NOT_UNUSUAL);
        }
        if (Objects.isNull(jwt)) {
            throw new BusinessException(ResultCode.USER_TOKEN_NOT_FUND);
        }
        return jwt;
    }


    /**
     * 根据Token获取username
     * @param token 用户token
     * @return username
     */
    public static String getUsername(String token) {
        DecodedJWT decode = verifyToken(token);
        Claim claim = decode.getClaim("username");
        if (Objects.isNull(claim) || StrUtil.isEmpty(claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
            throw new BusinessException(ResultCode.USER_TOKEN_NOT_UNUSUAL);
        }
        return claim.asString();
    }

    /**
     * 判断token是否过期
     * @param token 用户token
     * @return 布尔
     */
    public static boolean isExpires(String token) {
        DecodedJWT decode = verifyToken(token);
        return System.currentTimeMillis() > decode.getExpiresAt().getTime();
    }
}
