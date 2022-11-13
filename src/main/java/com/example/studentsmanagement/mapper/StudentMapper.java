package com.example.studentsmanagement.mapper;

import com.example.studentsmanagement.model.Student;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
  //select * from student where name LIKE %T%;
  @Select("select * from student where name LIKE #{name}")
  List<Student> getStudentsContainStrInName(@Param("name")String name);

  //select * from student where university_class_id IN
  //select id FROM university_class where year = 2021 AND number =1;
  @Select("select * from student where university_class_id IN" +
          "(select id from university_class where year = #{year} AND number = #{number})")
  List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);
}
