package com.ppc.blog.common;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.ObjectError;

import com.ppc.blog.common.Response;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Response methodArgumentNotValidHandler(HttpServletRequest req,
    MethodArgumentNotValidException exception) throws Exception {
    /* 只显示第一个字段的错误信息 */
    ObjectError error = exception.getBindingResult().getAllErrors().get(0); 
    return new Response("COMM_ERROR_VALID", error.getDefaultMessage());
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public Response methodNotSupportedHandler(HttpServletRequest req,
    HttpRequestMethodNotSupportedException exception) throws Exception {
    return new Response("COMM_ERROR_UNSUPPORT", "unsupported method");
  }

  @ExceptionHandler(value = Exception.class)
  public Response handler(HttpServletRequest req, Exception exception) {
    exception.printStackTrace();
    return new Response("COMM_ERROR_UNKNOWN", "something bad happend");
  }
}
