package com.nhnacademy.student.controller;

import com.nhnacademy.student.common.mvc.annotation.RequestMapping;
import com.nhnacademy.student.common.mvc.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/register.do")
public class StudentRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
