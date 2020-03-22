package cn.edu.njust.dev.ses.main.model;

import java.util.Date;

public class Borrow {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow.recordID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    private Integer recordid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow.readerID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    private Integer readerid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow.bookID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    private Integer bookid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow.borrowDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    private Date borrowdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow.returnDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    private Date returndate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Borrow(Integer recordid, Integer readerid, Integer bookid, Date borrowdate, Date returndate) {
        this.recordid = recordid;
        this.readerid = readerid;
        this.bookid = bookid;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Borrow() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow.recordID
     *
     * @return the value of borrow.recordID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Integer getRecordid() {
        return recordid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow.recordID
     *
     * @param recordid the value for borrow.recordID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow.readerID
     *
     * @return the value of borrow.readerID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Integer getReaderid() {
        return readerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow.readerID
     *
     * @param readerid the value for borrow.readerID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public void setReaderid(Integer readerid) {
        this.readerid = readerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow.bookID
     *
     * @return the value of borrow.bookID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Integer getBookid() {
        return bookid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow.bookID
     *
     * @param bookid the value for borrow.bookID
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow.borrowDate
     *
     * @return the value of borrow.borrowDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Date getBorrowdate() {
        return borrowdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow.borrowDate
     *
     * @param borrowdate the value for borrow.borrowDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow.returnDate
     *
     * @return the value of borrow.returnDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public Date getReturndate() {
        return returndate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow.returnDate
     *
     * @param returndate the value for borrow.returnDate
     *
     * @mbg.generated Sun Mar 22 10:16:48 HKT 2020
     */
    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }
}