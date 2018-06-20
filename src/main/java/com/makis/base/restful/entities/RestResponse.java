package com.makis.base.restful.entities;

import com.alibaba.fastjson.JSON;
import com.makis.base.misc.Constants;
import org.springframework.http.HttpStatus;

/**
 * Restful响应内容
 *
 * @author Alex
 */
public class RestResponse {
    /**
     * HTTP状态码
     */
    private int code;

    /**
     * 错误码
     */
    private int errno;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 新令牌
     */
    private String newToken;

    public RestResponse(HttpStatus status) {
        this(status.value(), null, null);
    }

    public RestResponse(HttpStatus status, int errno, String message) {
        this.code = status.value();
        this.errno = errno;
        this.message = message;
    }

    public RestResponse(HttpStatus status, String message, Object data) {
        this(status.value(), message, data);
    }

    public RestResponse(HttpStatus status, String message, Object data, String newToken) {
        this(status.value(), message, data, newToken);
    }

    public RestResponse(int code, String message, Object data) {
        this.code = code;
        this.errno = (HttpStatus.OK.value() == code) ? Constants.SUCCESS_CODE : Constants.ERROR_CODE;
        this.message = message;
        this.data = data;
    }

    public RestResponse(int code, String message, Object data, String newToken) {
        this.code = code;
        this.errno = (HttpStatus.OK.value() == code) ? Constants.SUCCESS_CODE : Constants.ERROR_CODE;
        this.message = message;
        this.data = data;
        this.newToken = newToken;
    }

    /**
     * JSONRESULT对象转换成string
     *
     * @return json string
     */
    public String toJSONString() {
        return JSON.toJSONString(this);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }
}

