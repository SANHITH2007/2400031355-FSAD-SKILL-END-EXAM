package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class App
{
    public static void main(String[] args)
    {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        // INSERT
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        vehicle l = new vehicle();
        l.setName("vehicle");
        l.setDescription("fourwheeler");
        l.setDate(new Date());
        l.setStatus("unsold");

        s.save(l);
        t.commit();
        s.close();

        System.out.println("Inserted vehicle: " + l.getId());

        // UPDATE
        Session s2 = sf.openSession();
        Transaction t2 = s2.beginTransaction();

        vehicle obj = s2.get(vehicle.class, l.getId());
        if(obj != null)
        {
            obj.setStatus("sold");   // updating status
            obj.setDescription("updated fourwheeler"); // updating description
            s2.update(obj);
            System.out.println("Updated vehicle: " + obj.getId());
        }

        t2.commit();
        s2.close();
    }
}
