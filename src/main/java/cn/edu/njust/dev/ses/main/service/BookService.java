package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.mapper.BookMapper;
import cn.edu.njust.dev.ses.main.model.Book;
import cn.edu.njust.dev.ses.main.model.BookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;
    public void insert(Book book){
        bookMapper.insert(book);
    }
    public void delete(Book book){
        bookMapper.deleteByPrimaryKey(book.getBookid());
    }
    public void search(Book book){
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andBooknameLike("%"+book.getBookname()+"%");
        bookExample.createCriteria().andPublisherLike("%"+book.getPublisher()+"%");
        bookExample.createCriteria().andBooktypeLike("%"+book.getBooktype()+"%");
        List<Book> books = bookMapper.selectByExample(bookExample);
    }

    public void update(Book book){
        BookExample bookExample = new BookExample();
        bookExample.createCriteria();
        bookMapper.updateByPrimaryKeySelective(book);
    }
}
