package com.icfcc.db.sys;

import java.util.Date;

public class SmMenu {
    private Integer iId;

    private String sName;

    private String sHref;

    private String sIcon;

    private String sTarget;

    private Integer iSort;

    private String sShow;

    private Integer iParent;

    private String sPermissioncode;

    private Date tsCreate;

    private Date tsLastupdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.i_id
     *
     * @return the value of sm_menu.i_id
     *
     * @mbggenerated
     */
    public Integer getiId() {
        return iId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.i_id
     *
     * @param iId the value for sm_menu.i_id
     *
     * @mbggenerated
     */
    public void setiId(Integer iId) {
        this.iId = iId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_name
     *
     * @return the value of sm_menu.s_name
     *
     * @mbggenerated
     */
    public String getsName() {
        return sName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_name
     *
     * @param sName the value for sm_menu.s_name
     *
     * @mbggenerated
     */
    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_href
     *
     * @return the value of sm_menu.s_href
     *
     * @mbggenerated
     */
    public String getsHref() {
        return sHref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_href
     *
     * @param sHref the value for sm_menu.s_href
     *
     * @mbggenerated
     */
    public void setsHref(String sHref) {
        this.sHref = sHref == null ? null : sHref.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_icon
     *
     * @return the value of sm_menu.s_icon
     *
     * @mbggenerated
     */
    public String getsIcon() {
        return sIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_icon
     *
     * @param sIcon the value for sm_menu.s_icon
     *
     * @mbggenerated
     */
    public void setsIcon(String sIcon) {
        this.sIcon = sIcon == null ? null : sIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_target
     *
     * @return the value of sm_menu.s_target
     *
     * @mbggenerated
     */
    public String getsTarget() {
        return sTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_target
     *
     * @param sTarget the value for sm_menu.s_target
     *
     * @mbggenerated
     */
    public void setsTarget(String sTarget) {
        this.sTarget = sTarget == null ? null : sTarget.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.i_sort
     *
     * @return the value of sm_menu.i_sort
     *
     * @mbggenerated
     */
    public Integer getiSort() {
        return iSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.i_sort
     *
     * @param iSort the value for sm_menu.i_sort
     *
     * @mbggenerated
     */
    public void setiSort(Integer iSort) {
        this.iSort = iSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_show
     *
     * @return the value of sm_menu.s_show
     *
     * @mbggenerated
     */
    public String getsShow() {
        return sShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_show
     *
     * @param sShow the value for sm_menu.s_show
     *
     * @mbggenerated
     */
    public void setsShow(String sShow) {
        this.sShow = sShow == null ? null : sShow.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.i_parent
     *
     * @return the value of sm_menu.i_parent
     *
     * @mbggenerated
     */
    public Integer getiParent() {
        return iParent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.i_parent
     *
     * @param iParent the value for sm_menu.i_parent
     *
     * @mbggenerated
     */
    public void setiParent(Integer iParent) {
        this.iParent = iParent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.s_permissioncode
     *
     * @return the value of sm_menu.s_permissioncode
     *
     * @mbggenerated
     */
    public String getsPermissioncode() {
        return sPermissioncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.s_permissioncode
     *
     * @param sPermissioncode the value for sm_menu.s_permissioncode
     *
     * @mbggenerated
     */
    public void setsPermissioncode(String sPermissioncode) {
        this.sPermissioncode = sPermissioncode == null ? null : sPermissioncode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.ts_create
     *
     * @return the value of sm_menu.ts_create
     *
     * @mbggenerated
     */
    public Date getTsCreate() {
        return tsCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.ts_create
     *
     * @param tsCreate the value for sm_menu.ts_create
     *
     * @mbggenerated
     */
    public void setTsCreate(Date tsCreate) {
        this.tsCreate = tsCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sm_menu.ts_lastupdate
     *
     * @return the value of sm_menu.ts_lastupdate
     *
     * @mbggenerated
     */
    public Date getTsLastupdate() {
        return tsLastupdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sm_menu.ts_lastupdate
     *
     * @param tsLastupdate the value for sm_menu.ts_lastupdate
     *
     * @mbggenerated
     */
    public void setTsLastupdate(Date tsLastupdate) {
        this.tsLastupdate = tsLastupdate;
    }
}