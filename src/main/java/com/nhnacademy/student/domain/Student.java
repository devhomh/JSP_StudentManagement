package com.nhnacademy.student.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import lombok.Getter;

@Getter
public class Student {
    private String id;

    private String name;

    private Gender gender;

    private int age;

    private String createAt;

    public Student(String id, String name, Gender gender, int age, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createAt = time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }


}
