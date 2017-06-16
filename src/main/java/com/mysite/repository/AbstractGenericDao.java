/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysite.repository;

import com.mysite.tools.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 *
 * @author Rémi
 * @param <E>
 * @param <K>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E,K extends Serializable> implements GenericDao<E,K> {
        
    protected Class<? extends E> entityClass;
    
    private Session session;
    
    /*public AbstractGenericDao(){
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }*/

    public AbstractGenericDao(Class c) {
      // this.entityClass =    (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      this.entityClass = c;
    }
    
    
     
    
    public E findById(final Serializable id){
        session = HibernateUtil.getSessionFactory().openSession();
        E result = null;
        try{
            session.beginTransaction();
            result = (E) session.get(this.entityClass, id);
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        }
        
        return result;
    }
    
    @Override
    public Serializable save(E entity){
        
        session = HibernateUtil.getSessionFactory().openSession();
        Serializable result = null;
        try{
            session.beginTransaction();
            result = session.save(entity);
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        }
        
        return result; 
    }
    
    @Override
    public void saveOrUpdate(E entity){
        
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        }        
        
    }
    
    @Override
    public void delete(E entity){
        
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        } 
        
    }
    
    @Override
    public List<E> findAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<E> result = null;
        try{
            session.beginTransaction();
            result = session.createCriteria(this.entityClass).list();
            //result =session.createQuery("from "+E.class.getName());
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        } 
        return result;
    }
    
    @Override
    public List<E> findAllByExample(E entity){
        Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
        
        session = HibernateUtil.getSessionFactory().openSession();
        List<E> result = null;
        try{
            session.beginTransaction();
            result = session.createCriteria(this.entityClass).add(example).list();
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        } 
        return result; 
    }
    
    @Override
    public E find(K key){
        
        session = HibernateUtil.getSessionFactory().openSession();
        E result = null;
        try{
            session.beginTransaction();
            result = (E) session.get(entityClass,key);
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        } 
        return result; 
         
    }
    
    /*filtres titre, date et location*/
    /*@Override
    public List<E> findBy(String column,Serializable param){
        session = HibernateUtil.getSessionFactory().openSession();
        List<E> result = null;
        try{
            String queryString =null
            Query findByQuery = session.createQuery("FROM :table WHERE :column ");
            findByQuery.setParameter("table", entityClass);
            session.beginTransaction();
            result = session.get(this.entityClass, param).list();
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }
        
        return result;
        
    }*/
    
    @Override
    public void clear(){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.clear();
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        } 
        
    }
    
    @Override
    public void flush(){
        
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.flush();
            session.getTransaction().commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction() != null){
                try{
                    session.getTransaction().rollback();
                }catch(HibernateException he2){
                    he2.printStackTrace();
                }
            }
        }finally{
            if(session != null){
                try{
                    session.close();
                }catch(HibernateException he){
                    he.printStackTrace();
                }
            }
            
        }
    }
}
