package com.nhnacademy.student;

import java.time.LocalDateTime;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1; i<=10; i++){
            String id = "student" + i;
            String name = "학생" + i;
            Gender gender = i % 2 == 0 ? Gender.M : Gender.F;
            int age = new Random().nextInt(10) + 21;
            studentRepository.save(new Student(id, name, gender, age, LocalDateTime.now()));
        }

        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}
