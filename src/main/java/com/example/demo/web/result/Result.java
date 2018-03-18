package com.example.demo.web.result;

import java.io.Serializable;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:08
 **/
public class Result <T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 744480715525805914L;

    private boolean success;

    private String errorMessage;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private Result() {}

    public static <T extends Serializable> Result<T> errorResult(String errorMessage) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public static <T extends Serializable> Result<T> successResult(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
}
