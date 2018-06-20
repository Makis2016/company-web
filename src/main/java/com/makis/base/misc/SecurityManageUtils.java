package com.makis.base.misc;

import com.makis.base.misc.Tracker;
import com.makis.base.shrio.SecurityRealm;
import org.apache.shiro.SecurityUtils;

/**
 * shiro工具类，主要用来获取用户ID
 */
public class SecurityManageUtils {
    public static SecurityRealm.ShiroUser getShiroUser() {
        return (SecurityRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        try {
            // TODO
            if (getShiroUser() != null) {
                return getShiroUser().getId();
            }
            else {
                return 1L;
            }
        }
        catch (Exception e) {
            Tracker.error(e);
            return null;
        }
    }
}