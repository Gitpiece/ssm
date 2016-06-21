package com.icfcc.web.entity;

public class ErrorBean extends ResultBean {

    private String errorCode;

    public ErrorBean() {
        this.setSuccess(false);
    }

    public ErrorBean(String errorCode) {
        this();
        this.setErrorCode(errorCode);
    }

    public ErrorBean(String errorCode, Object result) {
        this(errorCode);
        this.setResult(result);
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
