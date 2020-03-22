package cn.edu.njust.dev.ses.main.service;

import cn.edu.njust.dev.ses.main.dto.StudentDTO;
import cn.edu.njust.dev.ses.main.mapper.BookMapper;
import cn.edu.njust.dev.ses.main.mapper.StudentMapper;
import cn.edu.njust.dev.ses.main.model.Book;
import cn.edu.njust.dev.ses.main.model.BookExample;
import cn.edu.njust.dev.ses.main.model.Student;
import cn.edu.njust.dev.ses.main.model.StudentExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExportExcelService {
    @Autowired
    private BookMapper bookMapper;
    public List<StudentDTO> studentDTOS(){
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        List<Book> students = bookMapper.selectByExample(example);
        List<StudentDTO> studentDTOS = students.stream().map(q->{
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(q,studentDTO);
            return studentDTO;
        }).collect(Collectors.toList());
        return studentDTOS;
    }
}

