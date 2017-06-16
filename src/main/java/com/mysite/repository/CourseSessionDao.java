/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.repository;

import com.mysite.entity.CourseSession;
import com.mysite.entity.Location;
import com.mysite.tools.HibernateUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rémi
 */
public class CourseSessionDao extends AbstractGenericDao<CourseSession, Integer>{
  
    public CourseSessionDao(Class c) {
        super(c);
    }
   
    public List<CourseSession> findByColumns(Map<String,String> map){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date_start = null;
        Date date_end = null;
        Date date = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
                
        Criteria criteria = session.createCriteria(CourseSession.class,"coursesession")
                    .setFetchMode("coursesession.location", FetchMode.JOIN)
                    .createAlias("coursesession.location", "location")
                    .setFetchMode("coursesession.course", FetchMode.JOIN)
                    .createAlias("coursesession.course", "course");
        
        if(map.get("date_start") != null){
            try{
                date_start =  formatter.parse(map.get("date_start"));
            }catch(ParseException e){
                e.printStackTrace();
            }
            criteria.add(Restrictions.gt("startDate", date_start));
        }
        
        if(map.get("date_end") != null){
            try{
                date_end = formatter.parse(map.get("date_end"));
            }catch(ParseException e){
                e.printStackTrace();
            }
            criteria.add(Restrictions.lt("endDate", date_end));
        }
        
           if(map.get("date") != null){
            try{
                date = formatter.parse(map.get("date"));
            }catch(ParseException e){
                e.printStackTrace();
            }
            criteria.add(Restrictions.eq("startDate", date));
        }
        
        if(map.get("location_id") != null){
            criteria.add(Restrictions.eq("location.id", Integer.parseInt(map.get("location_id"))));
        }
        
        if(map.get("course_id") != null){
            criteria.add(Restrictions.eq("course.code", map.get("course_id")));
        }
        
        if(map.get("course_title")!=null){
            criteria.add(Restrictions.like("course.title", map.get("course_title"),MatchMode.ANYWHERE));
        }
        
        List<CourseSession> result = criteria.list();
        
        session.close();
        return result;
    }
    
            
    public List<CourseSession> findByDate(Date startDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(CourseSession.class, "coursesession")
                                   .setFetchMode("coursesession.location", FetchMode.JOIN)
                                   .createAlias("coursesession.location", "location")
                                   .setFetchMode("coursesession.course", FetchMode.JOIN)
                                   .createAlias("coursesession.course", "course")
                                   .add(Restrictions.gt("startDate", startDate))
                                   .addOrder(Order.asc("startDate"))
                                   .setMaxResults(3);
        List<CourseSession> result = criteria.list();
        session.close();
        
        return result;
    }
            
    
}
