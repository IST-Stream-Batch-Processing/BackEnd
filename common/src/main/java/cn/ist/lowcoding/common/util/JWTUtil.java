package cn.ist.lowcoding.common.util;

import cn.ist.lowcoding.common.model.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author loumoon
 * @date 2019-11-07 19:01
 */
public class JWTUtil {
    private static final String ALGORITHM_SECRET_KEY = "LOW_CODING_PLATFORM";

    private static final String ISSUER = "LOW_CODING_PLATFORM";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(ALGORITHM_SECRET_KEY);

    public static List<Role> decodeRole(String token) {
        String roles = JWT.decode(token).getClaim("roles").asString();
        return JsonUtil.readValue(roles, new TypeReference<List<Role>>(){});
    }

    public static String decodeAudience(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    public static Map<String, String> decodeUserFields(String token) {
        String userFields = JWT.decode(token).getClaim("userFields").asString();
        return JsonUtil.readValue(userFields, new TypeReference<Map<String, String>>() {});
    }

    public static void verify(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
        jwtVerifier.verify(token);
    }

    public static String encode(String userId, Set<Role> roles, Map<String,String> userFields) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withAudience(userId)
                .withClaim("roles", JsonUtil.writeValueAsString(roles))
                .withClaim("userFields", JsonUtil.writeValueAsString(userFields))
                .sign(ALGORITHM);
    }
}
