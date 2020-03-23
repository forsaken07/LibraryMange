package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.mapper.ReaderMapper;
import cn.edu.njust.dev.ses.main.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    @Autowired
    private ReaderMapper readerMapper;
    public void insert(Reader reader){
        readerMapper.insert(reader);
    }
    public void delete(Reader reader){
        readerMapper.deleteByPrimaryKey(reader.getReaderid());
    }
    public void search(Reader reader){
        readerMapper.selectByPrimaryKey(reader.getReaderid());
    }
    public void update(Reader reader){
        readerMapper.updateByPrimaryKeySelective(reader);
    }
}
