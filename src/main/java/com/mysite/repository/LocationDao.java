/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.repository;

import com.mysite.entity.Location;

/**
 *
 * @author Rémi
 */
public class LocationDao extends AbstractGenericDao<Location, Integer> {
    
    public LocationDao(Class c) {
        super(c);
    }
    
}
