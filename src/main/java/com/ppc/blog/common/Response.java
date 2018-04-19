package com.ppc.blog.common;

/*
 * Useage: 
 * 1. new Response(data)
 * 2. nwe Response(errorCode, errorMsg)
 */
public class Response {
  private class Error {
    private String code;
    private String msg;   

    public Error(String code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public String getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }

  private Error error;
  private Object data;

  public Response(Object data) {
    this.error = null;
    this.data = data;
  }

  public Response(String code, String msg) {
    this.error = new Error(code, msg);
    this.data = null;
  }

  public Error getError() {
    return error;
  }

  public Object getData() {
    return data;
  }
}
