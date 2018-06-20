package com.makis.base.exceptions;

import com.makis.base.misc.Constants;

/**
 * 名称相同异常类
 *
 * @author liujianning
 */
public class NameExistsException extends RuntimeException {
    public NameExistsException() {
        super(Constants.NAME_EXISTS);
    }

    public NameExistsException(String message) {
        super(message);
    }

    public NameExistsException(Throwable cause) {
        super(cause);
    }

    public NameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}