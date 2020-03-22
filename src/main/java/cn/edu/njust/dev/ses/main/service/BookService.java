package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.mapper.BookMapper;
import cn.edu.njust.dev.ses.main.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;
    public void add(Book book){
        bookMapper.insert(book);
    }
    public void delete(Book book){
        bookMapper.deleteByPrimaryKey(book.getBookid());
    }
    public void search(){

    }
    public void modify(){

    }
}
