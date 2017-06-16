/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.service;

import com.mysite.entity.CourseSession;
import com.mysite.repository.CourseSessionDao;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rémi
 */
public class CourseSessionService extends GenericServiceImpl<CourseSession, Integer> implements Serializable{
    
    public CourseSessionService(Class c){
        super(c);
    }
    public List<CourseSession> showSessionFromCriteria(Map<String,String> map){
        
        CourseSessionDao courseSessionDao = new CourseSessionDao(CourseSession.class);
        
        List<CourseSession> courseSessionList = courseSessionDao.findByColumns(map);
        
        return courseSessionList;
    }
    
    public List<CourseSession> showIncomingSessions(){
        
        CourseSessionDao courseSessionDao = new CourseSessionDao(CourseSession.class);
        List<CourseSession> courseSessionList = courseSessionDao.findByDate(new Date());
        
        return courseSessionList;
    }
}
