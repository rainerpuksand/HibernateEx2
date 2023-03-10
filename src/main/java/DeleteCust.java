import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class DeleteCust {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(cust.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		int custId;

		try {
			//Delete after retrieval of that Object
			custId = 1;
			session.beginTransaction();
			System.out.println("Get customer with id: " + custId);
			
			cust obj = session.get(cust.class, custId);

			System.out.println("Deleting customer: " + obj);
			session.delete(obj);





			//Delete customer directly with id=2
			/*System.out.println("Deleting customer id=2");
			custId = 2;

			session.beginTransaction();
			session.createQuery("delete from cust c where c.id= :custId")
					.setParameter("custId", custId)
					.executeUpdate();*/
			




			session.getTransaction().commit();
			System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
		}
		finally {
			factory.close();
		}
	}

}





