package com.example.studentsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false, name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "university_class_id")
  private UniversityClass universityClass;

  public Student(Long id, String name){
    this.id = id;
    this.name =name;
  }

  public Student(){}

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  public UniversityClass getUniversityClass() {
    return universityClass;
  }

  public void setUniversityClass(UniversityClass universityClass) {
    this.universityClass = universityClass;
  }

  @Override
  public String toString(){
    String str = "";
    str += "Primary ID: " +getId();
    str += " Name " + getName();
    return str;
   }
}
