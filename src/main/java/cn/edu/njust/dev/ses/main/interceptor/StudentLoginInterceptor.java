package cn.edu.njust.dev.ses.main.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StudentLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        //TODO
        User sessionUser = (User)request.getSession().getAttribute("logged_in_as");
//        Student student_Info = (Student) request.getSession().getAttribute("student_info");  student_Info == null||
        if(sessionUser == null|| !sessionUser.getType().equals("student")){
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        else{
            return true;
        }
    }
}
