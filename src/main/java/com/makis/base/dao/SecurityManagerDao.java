package com.makis.base.dao;

import com.makis.base.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SecurityManagerDao {
    class DaoProvider {

        private final static String USER_TABLE_NAME = "t_sys_user";

        private final static String ATTACHMENT_TABLE_NAME = "t_sys_attachment";

        private final static String CONFIG_TABLE_NAME = "t_sys_config";

        public String selectUserByUsername() {
            return new SQL() {{
                SELECT("su.id,su.user_name AS userName,su.password,su.salt,IFNULL(CONCAT(ct.attachment_server,sa.url,sa.name),CONCAT(ct.attachment_server,'images/face.jpg')) AS pictureUrl,su.city,su.remark");
                FROM(USER_TABLE_NAME + " su");
                INNER_JOIN(CONFIG_TABLE_NAME + " ct");
                LEFT_OUTER_JOIN(ATTACHMENT_TABLE_NAME + " sa ON su.picture_url = sa.id");
                WHERE("su.status = 1","ct.id = 1");
                AND();
                WHERE("su.user_name = #{userName}");
            }}.toString();
        }
    }

    @SelectProvider(type = SecurityManagerDao.DaoProvider.class, method = "selectUserByUsername")
    User selectUserByUsername(@Param("userName") String userName);
}