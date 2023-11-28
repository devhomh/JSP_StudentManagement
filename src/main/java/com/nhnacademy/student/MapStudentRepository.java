package com.nhnacademy.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository{
    private final Map<String, Student> studentMap = new ConcurrentHashMap<>();
    public void save(Student student){
        studentMap.putIfAbsent(student.getId(), student);
    }

    public void update(Student student){
        if(studentMap.containsKey(student.getId())){
            studentMap.replace(student.getId(), studentMap.get(student.getId()), student);
        }
    }

    public void deleteById(String id){
        studentMap.remove(id);
    }

    public Student getStudentById(String id){
        if(studentMap.containsKey(id)){
            return studentMap.get(id);
        }
        throw new NoSuchElementException("Not found student");
    }

    public List<Student> getStudents(){
        return new ArrayList<>(studentMap.values());
    }

    public boolean existById(String id){
        return studentMap.containsKey(id);
    }
}
