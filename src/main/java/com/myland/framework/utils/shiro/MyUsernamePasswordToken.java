package com.myland.framework.utils.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * @description: Shiro自定义用户验证TOKEN
 * @author: zhb
 * @create: 2018/3/19 0019
 */
public class MyUsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private String username;
    private char[] password;
    private boolean rememberMe;
    private String host;
    private String userType;



    public MyUsernamePasswordToken() {
        this.rememberMe = false;
    }

    public MyUsernamePasswordToken(String username, char[] password) {
        this(username, (char[])password, false, (String)null,(String)null);
    }

    public MyUsernamePasswordToken(String username, char[] password,String userType) {
        this(username, (char[])password, false, (String)null,userType);
        this.userType = userType;
    }

    public MyUsernamePasswordToken(String username, String password) {
        this(username, (char[])(password != null ? password.toCharArray() : null), false, (String)null,(String)null);
    }

    public MyUsernamePasswordToken(String username, char[] password, String host,String userType) {
        this(username, password, false, host,userType);
    }

    public MyUsernamePasswordToken(String username, String password, String host) {
        this(username, password != null ? password.toCharArray() : null, false, host,(String)null);
    }

    public MyUsernamePasswordToken(String username, char[] password, boolean rememberMe) {
        this(username, (char[])password, rememberMe, (String)null,(String)null);
    }

    public MyUsernamePasswordToken(String username, String password, boolean rememberMe) {
        this(username, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null,(String)null);
    }

    public MyUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,String userType) {
        this.rememberMe = false;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
        this.userType = userType;
    }

    public MyUsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host,(String)null);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return this.getUsername();
    }

    @Override
    public Object getCredentials() {
        return this.getPassword();
    }

    @Override
    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        if (this.password != null) {
            for(int i = 0; i < this.password.length; ++i) {
                this.password[i] = 0;
            }

            this.password = null;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" - ");
        sb.append(this.username);
        sb.append(", rememberMe=").append(this.rememberMe);
        if (this.host != null) {
            sb.append(" (").append(this.host).append(")");
        }

        return sb.toString();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
