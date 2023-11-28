package com.nhnacademy.student.servlet;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.StudentRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "/student/register");
        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo null check
        try {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            Gender gender = req.getParameter("gender").equals("male") ? Gender.M : Gender.F;
            int age = Integer.parseInt(req.getParameter("age"));

            //todo save 구현
            studentRepository.save(new Student(id, name, gender, age, LocalDateTime.now()));

            //todo redirect /student/view?id=student1
            resp.sendRedirect("/student/list");

        } catch(NullPointerException | NumberFormatException e){
            e.getStackTrace();
        }
    }

}
