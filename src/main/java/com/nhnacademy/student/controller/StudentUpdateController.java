package com.nhnacademy.student.controller;

import com.nhnacademy.student.common.domain.Gender;
import com.nhnacademy.student.common.domain.Student;
import com.nhnacademy.student.common.mvc.annotation.RequestMapping;
import com.nhnacademy.student.common.mvc.controller.Command;
import com.nhnacademy.student.common.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender").equals("male") ? Gender.M : Gender.F;
        int age = Integer.parseInt(req.getParameter("age"));

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("Re-check the information you entered.");
        }
        studentRepository.update(new Student(id, name, gender, age, LocalDateTime.now()));
        return "redirect:/student/view.do?id=" + id;
    }
}
