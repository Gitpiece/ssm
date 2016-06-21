package com.icfcc.web.entity;

import java.util.HashMap;
import java.util.Map;

public class SuccessBean extends ResultBean {
    public SuccessBean() {
        this.setSuccess(true);
    }

    public SuccessBean(Object result) {
        this();
        this.setResult(result);
    }

    /**
     * 单一结果返回的快速方法
     * 
     * @param k
     * @param v
     */
    public SuccessBean(String k, Object v) {
        this();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put(k, v);
        this.setResult(m);
    }
}
