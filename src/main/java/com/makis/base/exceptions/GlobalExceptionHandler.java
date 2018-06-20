package com.makis.base.exceptions;

import com.makis.base.misc.Constants;
import com.makis.base.misc.Tracker;
import com.makis.base.restful.entities.RestResponse;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Description:捕获所有Controller中@RequestMapping注解的方法执行过程中抛出的Exception
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { Exception.class })
    public void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        // TODO 增加异常类型
        if (ex instanceof UnauthenticatedException) {
            Tracker.info("=================unauthorized error=================");
            responseJson(response, Constants.UNAUTHORIZED);
        }
        else {
            Tracker.error(ex);
            responseJson(response, Constants.ERROR);
        }
    }

    /**
     * RequestParam 异常校验
     *
     * @param ex
     * @param request
     * @param response
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { NameExistsException.class,NotLoginException.class ,UpdatePswException.class})
    public void handleBussinessException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        responseJson(response, ex.getMessage());
    }

    /**
     * RequestParam 异常校验
     *
     * @param ex
     * @param request
     * @param response
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public void handleMissingException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        responseJson(response, Constants.ARG_NULL);
    }

    /**
     * 参数validation错误
     *
     * @param ex
     * @param request
     * @param response
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { BindException.class })
    public void handleBindException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        BindingResult errorResult = (BindingResult) ex;
        if (errorResult.hasErrors()) {
            List<ObjectError> list = errorResult.getAllErrors();
            if (list.size() > 0) {
                responseJson(response, list.get(0).getDefaultMessage());
            }
        }
        else {
            responseJson(response, Constants.ERROR);
        }
    }

    private void responseJson(HttpServletResponse response, String error) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(new RestResponse(HttpStatus.OK,-1,error).toJSONString());
            writer.flush();
        }
        catch (Exception e) {
            Tracker.error(e);
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}