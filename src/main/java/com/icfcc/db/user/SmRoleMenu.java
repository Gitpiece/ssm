package com.icfcc.db.user;

import java.util.Date;

public class SmRoleMenu {
    private Integer intRoleid;

    private Integer intMenuid;

    private Date tmsCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_role_menu.int_roleid
     *
     * @return the value of sm_role_menu.int_roleid
     *
     * @mbggenerated
     */
    public Integer getIntRoleid() {
        return intRoleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_role_menu.int_roleid
     *
     * @param intRoleid the value for sm_role_menu.int_roleid
     *
     * @mbggenerated
     */
    public void setIntRoleid(Integer intRoleid) {
        this.intRoleid = intRoleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_role_menu.int_menuid
     *
     * @return the value of sm_role_menu.int_menuid
     *
     * @mbggenerated
     */
    public Integer getIntMenuid() {
        return intMenuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_role_menu.int_menuid
     *
     * @param intMenuid the value for sm_role_menu.int_menuid
     *
     * @mbggenerated
     */
    public void setIntMenuid(Integer intMenuid) {
        this.intMenuid = intMenuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_role_menu.tms_create
     *
     * @return the value of sm_role_menu.tms_create
     *
     * @mbggenerated
     */
    public Date getTmsCreate() {
        return tmsCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_role_menu.tms_create
     *
     * @param tmsCreate the value for sm_role_menu.tms_create
     *
     * @mbggenerated
     */
    public void setTmsCreate(Date tmsCreate) {
        this.tmsCreate = tmsCreate;
    }
}