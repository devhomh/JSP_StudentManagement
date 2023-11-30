package com.nhnacademy.student.common.mvc.servlet;

import static javax.servlet.RequestDispatcher.*;

import com.nhnacademy.student.common.mvc.controller.Command;
import com.nhnacademy.student.common.mvc.controller.ControllerFactory;
import com.nhnacademy.student.controller.*;
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

        try {
            ControllerFactory factory = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
            log.info(req.getServletPath());
            Command command = (Command) factory.getBean(req.getMethod(), req.getServletPath());
            log.info(command.toString());
            log.info(command.execute(req, resp));
            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                view = view.substring(REDIRECT_PREFIX.length() + 1);
                log.info("redirect-url : {}", view);
                //todo `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view);
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 처리.
                log.info(view);
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch (Exception ex){
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }
    }

//    private Command resolveCommand(String servletPath, String method){
//        Command command = null;
//        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentListController();
//        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentViewController();
//        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentDeleteController();
//        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentUpdateFormController();
//        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentUpdateController();
//        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
//            command = new StudentRegisterFormController();
//        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
//            command = new StudentRegisterController();
//        }else if("/error.do".equals(servletPath)){
//            command = new ErrorController();
//        }
//        return command;
//    }
}
