package com.nhnacademy.student.servlet;

import com.nhnacademy.student.StudentRepository;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //todo id null check
            String id = req.getParameter("id");
            //todo student 조회
            req.setAttribute("student",studentRepository.getStudentById(id));
            //todo /student/view.jsp <-- forward
            RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
            rd.forward(req, resp);
        } catch(NullPointerException e){
            e.getStackTrace();
        }
    }

}
