package com.makis.base.shrio;

import com.makis.base.entities.User;
import com.makis.base.services.SecurityManagerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component("SecurityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private SecurityManagerService securityManagerService;

    /**
     * 获取用户角色、权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();

        return null;
    }

    /**
     * 获取用户权限列表
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        User user = securityManagerService.selectUserByUsername(username);
        if (user == null) {
            throw new AccountException("account error...");
        }

        return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getUserName(), upToken.isRememberMe(),user.getPictureUrl(), user.getCity(),user.getRemark()), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    public static class ShiroUser implements Serializable {

        /**
         * 用户id
         */
        public Long id;

        /**
         * 登录账户
         */
        public String username;

        /**
         * 是否记住我
         */
        public boolean rememberMe;

        /**
         * 权限编码
         */
        public Set<String> authorityList = new HashSet<String>();

        /**
         * 用户头像
         */
        public String pictureUrl;

        /**
         * 所在城市
         */
        public String city;

        /**
         * 当前用户所属活动ID 如：学校ID、公众号ID
         */
        public Long actionId;

        public String remark;

        public ShiroUser(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public ShiroUser(Long id, String username, boolean rememberMe, Set<String> authorityList) {
            this.id = id;
            this.username = username;
            this.rememberMe = rememberMe;
            this.authorityList = authorityList;
        }

        public ShiroUser(Long id, String username, Set<String> authorityList) {
            this.id = id;
            this.username = username;
            this.authorityList = authorityList;
        }

        public ShiroUser(Long id, String username, boolean rememberMe, String pictureUrl, String city,String remark) {
            this.id = id;
            this.username = username;
            this.rememberMe = rememberMe;
            this.pictureUrl = pictureUrl;
            this.city = city;
            this.remark = remark;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isRememberMe() {
            return rememberMe;
        }

        public void setRememberMe(boolean rememberMe) {
            this.rememberMe = rememberMe;
        }

        public Set<String> getAuthorityList() {
            return authorityList;
        }

        public void setAuthorityList(Set<String> authorityList) {
            this.authorityList = authorityList;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Long getActionId() {
            return actionId;
        }

        public void setActionId(Long actionId) {
            this.actionId = actionId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
