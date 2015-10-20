package com.icfcc.db.base;

/**
 * Created by admin on 2015/10/20.
 */
public interface BaseMapper<T,PK> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PK pk);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int insert(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int insertSelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    T selectByPrimaryKey(PK pk);

    T selectByAuthcode(String authcode);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(T record);
}
