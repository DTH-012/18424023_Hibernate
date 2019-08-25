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
import pojo.Sinhvien;
import util.HibernateUtil;

public class SinhVienDAO {
    public static List<Sinhvien> layDanhSachSinhvien() {
        List<Sinhvien> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Sinhvien sv";
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
    
    public static Sinhvien layThongTinSinhvien(String maSinhvien) {
        Sinhvien sv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sv = (Sinhvien) session.get(Sinhvien.class,
            maSinhvien);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sv;
    }
    
    public static boolean themSinhvien(Sinhvien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinSinhvien(sv.getMssv())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sv);
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
    
    public static boolean capNhatThongTinSinhvien(Sinhvien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (layThongTinSinhvien(sv.getMssv()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sv);
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
    
    public static boolean xoaSinhvien(String maSinhvien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Sinhvien sv = layThongTinSinhvien(maSinhvien);
        if(sv==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sv);
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
