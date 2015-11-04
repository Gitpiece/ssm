package com.icfcc.db.dal;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * test class
 * Created by admin on 2015/11/4.
 */
@Table(name = "sc")
public class SC {
    @Column(name = "s")
    Integer s;
    @Column(name = "c")
    Integer c;
    @Column(name = "score")
    Integer score;
    public SC(){}
    public SC(Integer s, Integer c){
        this.s = s;
        this.c = c;
    }
    public void setS(Integer s) {
        this.s = s;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getS() {
        return s;
    }

    public Integer getC() {
        return c;
    }

    public Integer getScore() {
        return score;
    }
}
