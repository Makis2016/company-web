package com.makis.base.exceptions;

import com.makis.base.misc.Constants;

/**
 * 密码修改异常
 *
 * @author liujianning
 */
public class UpdatePswException extends RuntimeException {
    public UpdatePswException() {
        super(Constants.ERROR);
    }

    public UpdatePswException(String message) {
        super(message);
    }

    public UpdatePswException(Throwable cause) {
        super(cause);
    }

    public UpdatePswException(String message, Throwable cause) {
        super(message, cause);
    }
}
