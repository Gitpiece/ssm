package com.icfcc.db.user;


import com.icfcc.db.anon.MyBatisDao;

@MyBatisDao
public interface SmUserAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer iId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int insert(SmUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int insertSelective(SmUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    SmUserAuth selectByPrimaryKey(Integer iId);

    SmUserAuth selectByAuthcode(String authcode);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SmUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sm_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SmUserAuth record);
}