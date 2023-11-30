package com.nhnacademy.student.controller;

import com.nhnacademy.student.common.mvc.annotation.RequestMapping;
import com.nhnacademy.student.common.mvc.controller.Command;
import com.nhnacademy.student.common.repository.StudentRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/update.do")
public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        req.setAttribute("student", studentRepository.getStudentById(id));
        return "/student/register.jsp";
    }
}