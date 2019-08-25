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
import pojo.Diem;
import util.HibernateUtil;

/**
 *
 * @author Arsenal
 */
public class DiemDAO {
    public static List<Diem> layDanhSachDiem() {
        List<Diem> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select d from Diem d";
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
    
    public static Diem layThongTinDiem(String mssv) {
        Diem d = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            d = (Diem) session.get(Diem.class,
            mssv);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return d;
    }
    
    public static boolean themDiem(Diem d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinDiem(d.getMssv())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(d);
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
    
    public static boolean capNhatThongTinDiem(Diem d) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinDiem(d.getMssv()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(d);
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
    
    public static boolean xoaDiem(String mssv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Diem d = layThongTinDiem(mssv);
        if(d==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(d);
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
