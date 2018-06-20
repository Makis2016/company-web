package com.makis.base.exceptions;

/**
 * 课程相关异常提示类
 *
 * @author liujianning
 */
public class CourseException extends RuntimeException{
    public CourseException(String message) {
        super(message);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }
}