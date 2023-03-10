import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


//@SuppressWarnings("unchecked") //mention this option
public class QueryCust {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(cust.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            //get all students in customertable table but ALWAYS use POJO in the query
            // that is why we are using "from cust" & NOT "from customertable"
            List<cust> custList = session.createQuery("from cust").getResultList();

            //display all customers
            for (cust obj : custList) {
                System.out.println(obj);
            }



            //get customer with firstname yo.. always use Java POJO thats why we query with
            //cust and firstName(as in Entity)... and not customertable and first_name(as in mySQL)
            custList = session.createQuery("from cust c where c.firstName='yo'").getResultList();


            for (cust obj : custList) {
                System.out.println("FirstName=yo....  " + obj);
            }

            // query with OR.....
            custList =  session.createQuery("from cust c where"
                            + " c.lastName='two' OR c.firstName='yo'").getResultList();

            System.out.println("\n OR Query................lastName=two OR firstName=yo");
            for (cust obj : custList) {
                System.out.println(obj);
            }


            // query with AND.....
            custList =  session.createQuery("from cust c where"
                    + " c.id>1 and c.firstName!='yo'").getResultList();

            System.out.println("\n AND Query..................id>1 AND firstName!=yo");
            for (cust obj : custList) {
                System.out.println(obj);
            }


            // query LIKE '%gmail.com'
            custList = session.createQuery("from cust c where"
                    + " c.email LIKE '%god.com'").getResultList();

            System.out.println("\n LIKE query for @god.com........................");
            for (cust obj : custList) {
                System.out.println(obj);
            }

            //query with variables
            int num_val=3;
            String str_val = "yo";
            custList = session.createQuery("from cust c where"
                        + " c.firstName = :str and c.id<= :num")
                    .setParameter("str", str_val)
                    .setParameter("num", num_val)
                    .getResultList();

            System.out.println("\n query with live Variables.....................");
            for (cust obj : custList) {
                System.out.println(obj);
            }


            //query with LIMIT From,Howmany

            custList = session.createQuery("from cust")
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            System.out.println("\n query with LIMITS.........................");
            for (cust obj : custList) {
                System.out.println(obj);
            }



            // commit transaction
            session.getTransaction().commit();
            System.out.println("---x-x-x-x-x-x-x-x-x-x-x-x---");
        }
        finally {
            factory.close();
        }
    }
}

//no need to call save after editing an Obj...
//https://stackoverflow.com/questions/9153995/when-i-use-a-jpa-querys-getresultlist-are-the-results-detached-or-managed




