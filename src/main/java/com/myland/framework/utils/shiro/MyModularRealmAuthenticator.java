package com.myland.framework.utils.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @description: 自定义多用户验证器
 * @author: zhb
 * @create: 2018/3/19 0019
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        assertRealmsConfigured();
        MyUsernamePasswordToken token=(MyUsernamePasswordToken)authenticationToken;
        //用户类型，realm在shiroconfig必须设置名称和loginType一致
        String loginType = token.getUserType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        HashMap<String,Realm> realmHashMap=new HashMap<>(realms.size());
        for (Realm realm : realms) {
            realmHashMap.put(realm.getName(),realm);
        }
        if (realmHashMap.get(loginType)!=null){
            return doSingleRealmAuthentication(realmHashMap.get(loginType), token);
        }else {
            return doMultiRealmAuthentication(realms, token);
        }
    }
}
