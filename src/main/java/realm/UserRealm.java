package realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    RedisTemplate redisTemplate;



    // 用户认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        Object principal = token.getPrincipal();
        String email = token.getUsername() ;
        if (!email.equals("2032118297@qq.com")) {
            return null;//就会报Unknown account的异常
        }
        return new SimpleAuthenticationInfo(principal,(String)redisTemplate.boundValueOps("emailCode").get(),"myRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("vip");
        return authorizationInfo;

    }
}
