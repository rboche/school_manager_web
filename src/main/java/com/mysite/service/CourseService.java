/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.service;

import com.mysite.entity.Course;
import java.io.Serializable;

/**
 *
 * @author Rémi
 */
 public class CourseService extends GenericServiceImpl<Course, Integer>{
   
     public CourseService(Class c){
         super(c);
     }
}
