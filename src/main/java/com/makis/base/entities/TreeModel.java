package com.makis.base.entities;

import java.util.List;

/**
 * 树model
 *
 * @author chenfuqian
 */
public class TreeModel implements java.io.Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 子类
     */
    private List children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
