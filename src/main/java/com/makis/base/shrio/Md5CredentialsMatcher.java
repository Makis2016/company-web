package com.makis.base.shrio;

import com.makis.base.misc.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class Md5CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(
            AuthenticationToken token,
            AuthenticationInfo info) {
        SimpleAuthenticationInfo simpleAuthenticationInfo = (SimpleAuthenticationInfo) info;
        String salt = new String(simpleAuthenticationInfo.getCredentialsSalt().getBytes());
        String tokenCredentials = MD5Utils.MD5LowerCase(token.getCredentials(), salt);
        Object accountCredentials = getCredentials(info);
        return equals(tokenCredentials, accountCredentials);
    }
}