package cn.edu.njust.dev.ses.main.controller.admin;

import cn.edu.njust.dev.ses.main.dto.ResultDTO;
import cn.edu.njust.dev.ses.main.dto.StudentDTO;
import cn.edu.njust.dev.ses.main.util.TempStorageObject;
import cn.edu.njust.dev.ses.main.util.excelparser.ExcelUniversalParser;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
@Controller
public class AdminImporterController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CCFEventMapper ccfEventMapper;
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    GradesEntryMapper gradesEntryMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    SelectRankEntryMapper selectRankEntryMapper;

    @ResponseBody
    @RequestMapping("/api/json/import_student_via_excel")
    public ResultDTO importStudentThroughExcel(HttpSession session, @RequestParam MultipartFile file){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        Teacher teacherInfo = (Teacher) session.getAttribute("teacher_info");
        if(sessionUser == null|| teacherInfo == null){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        List<StudentDTO> result = null;
        try{
            InputStream in = file.getInputStream();
            ExcelUniversalParser<StudentDTO> eup = new ExcelUniversalParser<>();
            result = eup.parseFrom(in, StudentDTO.class);
        }catch(NotOLE2FileException e) {
            return ResultDTO.errorOf(0, String.format("该 Excel 文件无效，请尝试用 Excel 重新另存为保存。信息：%s", e.getLocalizedMessage()));
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResultDTO.errorOf(0, String.format("发生异常（%s）", ex.getClass()), ex.getLocalizedMessage());
        }
        TempStorageObject<List<StudentDTO>> tempStorageObject = new TempStorageObject<>();
        tempStorageObject.setData(result);
        tempStorageObject.setCreationDate(new Date());

        tempStorageObject.setId(RandomUtils.nextInt());
        session.setAttribute("last_result_student_import", tempStorageObject);
        return ResultDTO.okOf(tempStorageObject);
    }

    @ResponseBody
    @RequestMapping("/api/json/import_grades_via_excel")
    public ResultDTO importGradesEntriesThroughExcel(HttpSession session, @RequestParam MultipartFile file){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        Teacher teacherInfo = (Teacher) session.getAttribute("teacher_info");
        if(sessionUser == null|| teacherInfo == null){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        List<GradesEntryDTO> result = null;
        try{
            InputStream in = file.getInputStream();
            ExcelUniversalParser<GradesEntryDTO> eup = new ExcelUniversalParser<>();
            result = eup.parseFrom(in, GradesEntryDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultDTO.errorOf(0, String.format("发生异常（%s）", ex.getClass()), ex.getLocalizedMessage());
        }

        for(GradesEntryDTO gradesEntryDTO: result){//根据证件号将考试成绩与学生表连起来，获得学生ID
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andIdNoEqualTo(gradesEntryDTO.getIdNo());
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(!students.isEmpty())
                gradesEntryDTO.setStudentId(students.get(0).getStudentId());
        }


        TempStorageObject<List<GradesEntryDTO>> tempStorageObject = new TempStorageObject<>();
        tempStorageObject.setData(result);
        tempStorageObject.setCreationDate(new Date());

        tempStorageObject.setId(RandomUtils.nextInt());
        session.setAttribute("last_result_grades_import", tempStorageObject);
        return ResultDTO.okOf(tempStorageObject);
    }

    @ResponseBody
    @RequestMapping("/api/json/import_select_ranks_via_excel")
    public ResultDTO importSelectRankEntryThroughExcel(HttpSession session, @RequestParam MultipartFile file){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        if(sessionUser == null|| !sessionUser.getType().equals("associate")){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        //TODO
        //注：上传学生选拔考试的成绩
        List<SelectRankEntryDTO> result;
        try{
            InputStream in = file.getInputStream();
            ExcelUniversalParser<SelectRankEntryDTO> eup = new ExcelUniversalParser<>();
            result = eup.parseFrom(in, SelectRankEntryDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultDTO.errorOf(0, String.format("发生异常（%s）", ex.getClass()), ex.getLocalizedMessage());
        }

        for(SelectRankEntryDTO selectRankEntryDTO: result){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andIdNoEqualTo(selectRankEntryDTO.getIdNo());
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(!students.isEmpty())
                selectRankEntryDTO.setUid(students.get(0).getUid());
        }


        TempStorageObject<List<SelectRankEntryDTO>> tempStorageObject = new TempStorageObject<>();
        tempStorageObject.setData(result);
        tempStorageObject.setCreationDate(new Date());

        tempStorageObject.setId(RandomUtils.nextInt());
        session.setAttribute("last_result_select_ranks_import", tempStorageObject);
        return ResultDTO.okOf(tempStorageObject);
    }

    @ResponseBody
    @RequestMapping("/api/json/confirm_rank_import")
    public ResultDTO confirmRankImport(HttpSession session, @RequestParam Integer confirmId, @RequestParam Integer eid){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        if(sessionUser == null|| !sessionUser.getType().equals("associate")){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        //TODO
        TempStorageObject<List<SelectRankEntryDTO>> tempStorageObject = (TempStorageObject<List<SelectRankEntryDTO>>) session.getAttribute("last_result_select_ranks_import");
        if(tempStorageObject == null){
            return ResultDTO.errorOf(0, "上次上传的文件已经过期，请重新上传。");
        }
        if(!tempStorageObject.getId().equals(confirmId)){
            return ResultDTO.errorOf(0, "确认 ID 不正确，请重新上传文件。");
        }
        long count = 0;
        for(SelectRankEntryDTO selectRankEntryDTO : tempStorageObject.getData()){
            SelectRankEntry selectRankEntry = new SelectRankEntry();
            BeanUtils.copyProperties(selectRankEntryDTO, selectRankEntry);
            selectRankEntry.setEid(eid);
            if(selectRankEntry.getUid() == null)
                continue;
            try {
                count += selectRankEntryMapper.insertSelective(selectRankEntry);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
        session.removeAttribute("last_result_select_ranks_import");
        return ResultDTO.okOf(count);
    }



    @ResponseBody
    @RequestMapping("/api/json/confirm_grades_import")
    public ResultDTO confirmGradesImport(HttpSession session, @RequestParam Integer confirmId, @RequestParam Integer eid){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        Teacher teacherInfo = (Teacher) session.getAttribute("teacher_info");
        if(sessionUser == null|| teacherInfo == null){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        TempStorageObject<List<GradesEntryDTO>> tempStorageObject = (TempStorageObject<List<GradesEntryDTO>>) session.getAttribute("last_result_grades_import");
        if(tempStorageObject == null){
            return ResultDTO.errorOf(0, "上次上传的文件已经过期，请重新上传。");
        }
        if(!tempStorageObject.getId().equals(confirmId)){
            return ResultDTO.errorOf(0, "确认 ID 不正确，请重新上传文件。");
        }
        long count = 0;
        for(GradesEntryDTO gradesEntryDTO : tempStorageObject.getData()){
            GradesEntry gradesEntry = new GradesEntry();
            BeanUtils.copyProperties(gradesEntryDTO, gradesEntry);
            gradesEntry.setEid(eid);
            try {
                count += gradesEntryMapper.insertSelective(gradesEntry);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
        session.removeAttribute("last_result_grades_import");
        return ResultDTO.okOf(count);
    }



    @ResponseBody
    @RequestMapping("/api/json/confirm_student_import")
    public ResultDTO confirmStudentImport(HttpSession session, @RequestParam Integer confirmId){
        User sessionUser = (User) session.getAttribute("logged_in_as");
        Teacher teacherInfo = (Teacher) session.getAttribute("teacher_info");
        if(sessionUser == null|| teacherInfo == null){
            return ResultDTO.errorOf(0, "用户未登录或用户类型不正确。");
        }
        TempStorageObject<List<StudentDTO>> tempStorageObject = (TempStorageObject<List<StudentDTO>>) session.getAttribute("last_result_student_import");
        if(tempStorageObject == null){
            return ResultDTO.errorOf(0, "上次上传的文件已经过期，请重新上传。");
        }
        if(!tempStorageObject.getId().equals(confirmId)){
            return ResultDTO.errorOf(0, "确认 ID 不正确，请重新上传文件。");
        }
        long count = 0;
        for(StudentDTO studentDTO : tempStorageObject.getData()){
            Student student = new Student();
            BeanUtils.copyProperties(studentDTO, student);
            try {
                studentService.insertStudentRecordAndUpdateGradesEntry(student);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }
            count ++;
        }
        session.removeAttribute("last_result_student_import");
        return ResultDTO.okOf(count);
    }
}
