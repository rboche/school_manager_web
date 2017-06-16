/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.repository;

import com.mysite.tools.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Rémi
 * @param <E>
 * @param <K>
 */

public interface GenericDao<E,K extends Serializable> {
    
    Serializable save(E entity);
    
    public void saveOrUpdate(E entity);
    
    void delete(E entity);
    
    //void deleteAll();
    
    List<E> findAll();
    
    List<E> findAllByExample(E entity);
    
    //List<E> findBy(String column,Serializable param);
    
    public E find(K key);
    
    void clear();
    
    void flush();
    
    
}
