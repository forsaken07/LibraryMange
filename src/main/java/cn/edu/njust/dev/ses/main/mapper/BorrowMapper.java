package cn.edu.njust.dev.ses.main.mapper;

import cn.edu.njust.dev.ses.main.model.Borrow;

public interface BorrowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    int deleteByPrimaryKey(Integer recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    int insert(Borrow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    int insertSelective(Borrow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    Borrow selectByPrimaryKey(Integer recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    int updateByPrimaryKeySelective(Borrow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 11:11:37 HKT 2020
     */
    int updateByPrimaryKey(Borrow record);
}