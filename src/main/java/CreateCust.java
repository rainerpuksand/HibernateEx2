import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCust {

    public static void main(String[] args) {

        //session factory from config
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(cust.class)
                                    .buildSessionFactory();

        //session from its factory
        Session session = factory.getCurrentSession();

        try {
            //make java object
            System.out.println("Creating new Cust Java object...");
            cust custObj = new cust("yo54", "four", "god@god.com");
            //cust custObj = new cust("yo", "three", "god@OMG.com");
            //cust custObj = new cust("yo2", "two", "my_god@god.com");
            //cust custObj = new cust("yo", "one", "god1@god.com");

            //transaction
            session.beginTransaction();

            // INSERT/create cust record via the java object
            System.out.println("Inserting the customer...");
            //session.save(custObj);

            //if you ever want the id of the new record entered
            int newId = (Integer) session.save(custObj);
            if(newId==0) {
                System.out.println("your session.save() failed.");
            }
            else {
                System.out.println("session.save() returned " + newId);
            }

           //This also saves an Obj .. but returns void
           //session.persist(custObj);



            //commit transaction
            session.getTransaction().commit();

            System.out.println("End  --x x x x x x x x x-- ");
        }
        finally {
            factory.close();
        }

    }
}
//FOR BATCH INSERTS/UPDATES... even from ArrayLists
// https://mail.codejava.net/frameworks/hibernate/how-to-execute-batch-insert-update-in-hibernate