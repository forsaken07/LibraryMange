package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.mapper.BorrowMapper;
import cn.edu.njust.dev.ses.main.model.Book;
import cn.edu.njust.dev.ses.main.model.Borrow;
import cn.edu.njust.dev.ses.main.model.BorrowExample;
import cn.edu.njust.dev.ses.main.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 与借阅相关的服务的内容
 * 1.增加借阅记录
 * 2.修改借阅记录（还书）
 * 3.查询借阅记录（通过某本书，某个人）
 *
 */

@Service
public class BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void insert(Reader reader, Book book) throws ParseException {
        Borrow borrow = new Borrow();
        borrow.setBookid(book.getBookid());
        borrow.setReaderid(reader.getReaderid());
        borrow.setBorrowdate(formatter.parse(formatter.format(new Date())));
        borrowMapper.insert(borrow);
    }

    public void delete(Reader reader,Book book){
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andBookidEqualTo(book.getBookid());
        borrowExample.createCriteria().andReaderidEqualTo(reader.getReaderid());
        List<Borrow> borrows=borrowMapper.selectByExample(borrowExample);
        borrows.sort(Comparator.comparingInt(Borrow::getBookid));
        borrowMapper.deleteByPrimaryKey(borrows.get(borrows.size()-1).getRecordid());
    }

    public List<Borrow> search(Reader reader,Book book){
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andReaderidEqualTo(reader.getReaderid());
        borrowExample.createCriteria().andBookidEqualTo(book.getBookid());
        borrowExample.createCriteria().andBooknameEqualTo(book.getBookname());
        List<Borrow> borrows = borrowMapper.selectByExample(borrowExample);
        return borrows;
    }

    public void update(Reader reader,Book book) throws ParseException {
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andReaderidEqualTo(reader.getReaderid());
        borrowExample.createCriteria().andBookidEqualTo(book.getBookid());
        List<Borrow> borrows=borrowMapper.selectByExample(borrowExample);
        borrows.sort(Comparator.comparingInt(Borrow::getBookid));
        int len = borrows.size();
        Borrow borrow = new Borrow();
        borrow.setRecordid(borrows.get(len-1).getRecordid());
        borrow.setReturndate(formatter.parse(formatter.format(new Date())));
        borrowMapper.updateByPrimaryKeySelective(borrow);
    }
}
