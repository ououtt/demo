package com.example.demo.web.result;

import java.io.Serializable;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:07
 **/
public class ListResult <T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 134443470211333394L;

    private List<T> data;

    private boolean success;

    private String errorMessage;

    public List<T> getData() {
        return data;
    }

    private void setData(List<T> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private ListResult() {}

    public static <T extends Serializable> ListResult<T> errorResult(String errorMessage) {
        ListResult<T> result = new ListResult<>();
        result.setSuccess(false);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public static <T extends Serializable> ListResult<T> successResult(List<T> data) {
        ListResult<T> result = new ListResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
}

