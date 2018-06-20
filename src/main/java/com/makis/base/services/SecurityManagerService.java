package com.makis.base.services;

import com.makis.base.dao.SecurityManagerDao;
import com.makis.base.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityManagerService {
    @Autowired
    private SecurityManagerDao mapper;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public User selectUserByUsername(String username) {
        return mapper.selectUserByUsername(username);
    }
}
