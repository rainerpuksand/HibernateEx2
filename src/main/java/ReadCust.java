
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadCust {

    public static void main(String[] args) {


        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(cust.class)
                .buildSessionFactory();

        try{
            //session and start transaction
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //get Cust based on the id: primary key
            System.out.println("\nGetting customer with id: " + 2);

            cust obj = session.get(cust.class, 2);
            System.out.println("Get complete: " + obj);
            //if you ever need the id or any other value in the Entity's(POJO's)
            //System.out.println("just call obj.getId: " + obj.getId());


            //.load() does the same thing as .get().... but .get() wont throw an error if no
            //record is found, it will return null. Load will throw an error
            // .load() doesnt always go to the db(might do a proxy).. it might give you
            //something that identifies with the obj..
            // but .get() will always make a trip to the db and return the latest obj
            //and if the obj is already in the cache it will give you a fully initialized obj.
            //IF YOU ARE NOT SURE IF THE OBJ HAS BEEN CREATED THEN JUST USE .GET()



            //cust obj1 = session.load(cust.class,8);
            //System.out.println(obj1); //... here .load() will throw an error unlike get()



            // commit the transaction
            session.getTransaction().commit();

            System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-");
        }
        catch (Exception e){
            System.out.println("ERROR.....");
        }
        finally {
            factory.close();
        }
    }

}





