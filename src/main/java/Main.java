import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {

        // sessionFactory должна быть только одна, можно создать для ее создания отдельный статический класс (синглтон)
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // code case 1 print name and students count

        Session session = sessionFactory.openSession();
/*
        Course course = session.get(Course.class, 1);

        System.out.println("\"" + course.getName() + "\"" + " " + course.getStudentCount());
*/
        // code case 2 data adding

        Transaction transaction1 = session.beginTransaction();

        Course course1 = new Course();
        course1.setName("Новый курс");
        course1.setType(CourseType.BUSINESS);
        course1.setTeacherId(1);

        session.save(course1);
        System.out.println(course1.getId());
        transaction1.commit();

        // code case 3 data replacement

        Transaction transaction2 = session.beginTransaction();

        Course course2 = session.get(Course.class, 45);
        course2.setName("Измененный курс");

        transaction2.commit();

        // code case 4 data delete

        Transaction transaction3 = session.beginTransaction();

        Course course3 = session.get(Course.class, 56);
        session.delete(course3);

        transaction3.commit();

        sessionFactory.close();

    }
}
