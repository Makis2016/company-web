package com.makis.base.entities;

import java.util.List;

/**
 * 树形结构区域
 * Created by dicky on 2017/7/7.
 */
public class SelectTreeObj {
    private long key;
    private long pKey;
    private String label;
    private String value;
    private boolean disable;
    private List<SelectTreeObj> children;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getpKey() {
        return pKey;
    }

    public void setpKey(long pKey) {
        this.pKey = pKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public List<SelectTreeObj> getChildren() {
        return children;
    }

    public void setChildren(List<SelectTreeObj> children) {
        this.children = children;
    }
}
