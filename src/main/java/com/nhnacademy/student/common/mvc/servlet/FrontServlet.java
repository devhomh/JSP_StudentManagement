package com.nhnacademy.student.common.mvc.servlet;

import static javax.servlet.RequestDispatcher.*;

import com.nhnacademy.student.common.mvc.controller.Command;
import com.nhnacademy.student.common.mvc.controller.ControllerFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        ControllerFactory factory = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
        Command command = (Command) factory.getBean(req.getMethod(), req.getServletPath());
        String view = command.execute(req, resp);

        try {
            if (view.startsWith(REDIRECT_PREFIX)) {
                view = view.substring(REDIRECT_PREFIX.length() + 1);
                log.info("redirect-url : {}", view);
                resp.sendRedirect(view);
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 처리.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch (Exception ex){
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req,resp);
        }
    }
}
