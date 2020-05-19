/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import newpackage.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author javier
 */
public class Controlador {

    public List<Film> listar(){
        List<Film> films = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        if(session != null){
            Query query = session.createQuery("from Film");
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
    
    public List<Film> buscarPeliculas(String pelicula){
        List<Film> films = null;
        pelicula = "'%" + pelicula + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("From Film film where film.title like " + pelicula);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
    
    public List<Film> buscarPorCategoria(String categoria){
        List<Film> films = null;
        categoria = "'%" + categoria + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("select fc.film " +
                "from FilmCategory fc " +
                "where fc.category.name like " + categoria);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        
        return films;
    }
    
    public List<Film> buscarPorCaracteristicasEspeciales(String caracteristicas){
        List<Film> films = null;
        caracteristicas = "'%" + caracteristicas + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("from Film f where f.specialFeatures like " + caracteristicas);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
    
    public List<String> categorias(){
        List<String> categorias = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("select distinct name from Category");
            categorias = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return categorias;
    }
    
    public ArrayList<String> arrayCategorias(){
        List<String> categorias = categorias();
        ArrayList<String> aux = new ArrayList<>();
        
        for(String str: categorias){
            aux.add(str);
        }
        
        return aux;
    }
    
    public List<String> caracteristicas(){
        List<String> caracteristicas = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("select specialFeatures from Film");
            caracteristicas = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return caracteristicas;
    }
    
    public ArrayList<String> arrayCaracteristicas(){
        ArrayList<String> aux = new ArrayList<>();
        List<String> list = caracteristicas();
        
        for(String str: list){
            aux.add(str);
        }
        
        
        return aux;
    }
    
    public ArrayList<String> distinctCaracteristicas(){
        ArrayList<String> caracteristicas = new ArrayList<>();
        
        caracteristicas.add("Trailers");
        caracteristicas.add("Commentaries");
        caracteristicas.add("Behind the Scenes");
        caracteristicas.add("Deleted Scenes");
        
        return caracteristicas;
    }
}