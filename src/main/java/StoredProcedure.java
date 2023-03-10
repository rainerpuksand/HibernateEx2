import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class StoredProcedure {

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


            //for a simple return of records from a SP
            /*Query query = session.createSQLQuery("CALL all_recs()")
                    .addEntity(cust.class);

            List result = query.list();
            for(int i=0; i<result.size(); i++){
                cust myCust = (cust)result.get(i);
                System.out.println(myCust);
            }*/



            //when records are returned with IN Parameter
            /*Query query = session.createSQLQuery("CALL only_in_para(:X)")
                    .setParameter("X", "yo")
                    .addEntity(cust.class);

            List result = query.list();
            for(int i=0; i<result.size(); i++) {
                cust myCust = (cust) result.get(i);
                System.out.println(myCust);
            }*/





          //if you want to get back particular value from output parameters
            StoredProcedureQuery query = session.createStoredProcedureQuery("new_procedure")
                    .registerStoredProcedureParameter("myIn", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("ans1", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("ans2", Integer.class, ParameterMode.OUT)
                    .setParameter("myIn", "yo");

            query.execute();
            int ans1 = (int) query.getOutputParameterValue("ans1");
            int ans2 = (int) query.getOutputParameterValue("ans2");
            System.out.println(ans1);
            System.out.println(ans2);


            // commit transaction
            session.getTransaction().commit();
            session.close();
            System.out.println("---x-x-x-x-x-x-x-x-x-x-x-x---");
        }
        finally {
            factory.close();
        }
    }
}
