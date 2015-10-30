//package com.icfcc.db;
//
//import java.io.Serializable;
//import java.util.Date;
//
//public abstract class Dto<K extends Serializable> {
//
//    private Date createdAt;
//    private Date updatedAt;
//
//    public abstract K getKey();
//
//    public Dto setCreatedAt(Date datetime) {
//        this.createdAt = datetime;
//        return this;
//    }
//
//    public Dto setUpdatedAt(Date datetime) {
//        this.updatedAt = datetime;
//        return this;
//    }
//
//    public final Date getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public final Date getUpdatedAt() {
//        return this.updatedAt;
//    }
//}
