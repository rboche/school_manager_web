/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.service;

import com.mysite.entity.Client;
import com.mysite.repository.ClientDao;
import java.util.List;

/**
 *
 * @author Rémi
 */
public class ClientService  extends GenericServiceImpl<Client,Integer>{

    public ClientService(Class c) {
        super(c);
    }
    
}
