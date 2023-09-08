package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 *  //  @RestControllerAdvice 标记一个类，
 *      该类将成为应用程序中所有 @Controller 或 @RestController
 *      注解的控制器抛出的异常的统一处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler  //注解来处理特定类型的异常
    public Result<String> exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){

        // Duplicate entry 'zhangsan' for key 'employee.idx_username'
        String message = ex.getMessage();
        // 添加用户重复
        if (message.contains("Duplicate entry")){
            String[] s = message.split(" ");
            String username = s[2];
            return Result.error(username+ MessageConstant.ALREADY_EXIST);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
