package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.mapper.BorrowMapper;
import cn.edu.njust.dev.ses.main.model.Book;
import cn.edu.njust.dev.ses.main.model.Borrow;
import cn.edu.njust.dev.ses.main.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void add(Reader reader, Book book) throws ParseException {
        Borrow borrow = new Borrow();
        borrow.setBookid(book.getBookid());
        borrow.setReaderid(reader.getReaderid());
        borrow.setBorrowdate(formatter.parse(formatter.format(new Date())));
        borrowMapper.insert(borrow);
    }
    public void delete(Reader reader,Book book){
        Borrow borrow = new Borrow();
        borrowMapper.deleteByPrimaryKey(borrow.getRecordid());
    }
    public void search(){

    }
    public void modify(Reader reader,Book book){

    }
}
