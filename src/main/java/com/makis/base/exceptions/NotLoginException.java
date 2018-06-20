package com.makis.base.exceptions;

import com.makis.base.misc.Constants;

/**
 * 未登录异常类
 *
 * @author liujianning
 */
public class NotLoginException extends RuntimeException {
    public NotLoginException() {
        super(Constants.NOT_LOGIN_MSG);
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}