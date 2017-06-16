/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.service;

import com.mysite.entity.Location;
import java.io.Serializable;

/**
 *
 * @author Rémi
 */
public class LocationService extends GenericServiceImpl<Location, Integer> implements Serializable{
    
    public LocationService(Class c){
        super(c);
    }
    
    
}
