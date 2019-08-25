/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg18424023_hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Sinhvien;
import util.HibernateUtil;
import javax.swing.JFrame;

/**
 *
 * @author Arsenal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        List<Sinhvien> ds = null;
//        Session session = HibernateUtil.getSessionFactory()
//        .openSession();
//        
//        String hql = "select sv from Sinhvien sv";
//        Query query = session.createQuery(hql);
//        ds = query.list();
//        
//        for (int i = 0; i < ds.size(); i++) {
//            System.out.println(ds.get(i).getHoTen());
//        }
        
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.setSize(450, 100);
		loginScreen.setVisible(true);
		loginScreen.setResizable(false);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
