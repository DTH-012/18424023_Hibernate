/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Monhoc;
import util.HibernateUtil;

/**
 *
 * @author Arsenal
 */
public class MonHocDAO {
    public static List<Monhoc> layDanhSachMonhoc() {
        List<Monhoc> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select mh from Monhoc mh";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static Monhoc layThongTinMonhoc(String maMH) {
        Monhoc mh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            mh = (Monhoc) session.get(Monhoc.class,
            maMH);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return mh;
    }
    
    public static boolean themMonhoc(Monhoc mh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinMonhoc(mh.getMaMh())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(mh);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean capNhatThongTinMonhoc(Monhoc mh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinMonhoc(mh.getMaMh()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(mh);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean xoaSinhVien(String maMH) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Monhoc mh = layThongTinMonhoc(maMH);
        if(mh==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(mh);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
