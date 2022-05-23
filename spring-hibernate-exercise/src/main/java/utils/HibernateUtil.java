package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (sf == null) {
			sf = new Configuration().configure().buildSessionFactory();
		}

		return sf;
	}
}
