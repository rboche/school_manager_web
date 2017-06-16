/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.service;

import java.util.List;

/**
 *
 * @author Rémi
 * @param <E>
 * @param <K>
 */
public interface GenericService<E, K> {
    
    public List<E> getAll();
    public E get(K id);
    public void add(E entity);
    public void update(E entity);
    public void delete(E entity);
    //public void deleteAll();
    
}
