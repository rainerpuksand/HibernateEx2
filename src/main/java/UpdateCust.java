import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class UpdateCust {

	public static void main(String[] args) {

		//session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(cust.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			//part 1: when you need the Object retieved before you update

			/*int numId = 1;
			session.beginTransaction();

			System.out.println("Getting cust with id: " + numId);

			cust obj = session.get(cust.class, numId);

			System.out.println("Updating student...");
			int id = obj.getId();
			obj.setFirstName("YoH"+id);//now Name is a new concatenation of Id & YoH
			session.getTransaction().commit();*/ //... and now automatically saved





			/*// part 2: when you just need an immediate update without obj retrieval

			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Update all emails ");

			session.createQuery("update cust c set c.email='OMG@OMG.com'")
					.executeUpdate();

			// commit the transaction
			session.getTransaction().commit();*/



			// part 3: saveOrUpdate - is when you insert a record even if it doesnt exist
			//make obj from its class and then...
			//session.saveOrUpdate(obj);




			System.out.println("x-x-x-x-x-x-x--x-x-x-x-x-xx");
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally {
			factory.close();
		}
	}
}





