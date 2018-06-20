package com.makis.base.entities;

import com.baomidou.mybatisplus.annotations.TableName;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description： 配置表
 *
 * @author chenfuqian
 */
@Entity
@Table(name = "t_sys_config")
@TableName("t_sys_config")
public class Config implements Serializable {

    @Id
    @Column(unique = true, nullable = false, columnDefinition = "bigint(20) COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '附件服务器地址'")
    private String attachmentServer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentServer() {
        return attachmentServer;
    }

    public void setAttachmentServer(String attachmentServer) {
        this.attachmentServer = attachmentServer;
    }
}
