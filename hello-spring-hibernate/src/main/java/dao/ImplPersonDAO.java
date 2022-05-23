package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Person;
import utils.HibernateUtil;

public class ImplPersonDAO implements IPersonDAO {

	@Override
	public List<Person> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			// Query HQL (Hibernate Query Language)
			@SuppressWarnings("unchecked")
			List<Person> listPeople = ss.createQuery("from Person").list();

			// Close connect
			ss.close();

			return listPeople;

		} catch (Exception e) {
			System.out.println("Create person failed!");
			e.printStackTrace();

			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> search(String name) {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			String HQL = "from Person where id > 0 and";

			if (!name.isEmpty()) {
				HQL += " name like :searchName";
			}

			List<Person> listPeople = ss.createQuery(HQL).setParameter("searchName", "%" + name + "%").list();

			// Close connect
			ss.close();

			return listPeople;

		} catch (Exception e) {
			System.out.println("Create person failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Person p) {

		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(p);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create person failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(Person p) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(p);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update person information failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Person detail(int personId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Person p = (Person) ss.createQuery("from Person where id = :personId").setParameter("personId", personId)
					.getSingleResult();

			ss.close();

			return p;
		} catch (Exception e) {
			System.out.println("Get person width ID " + personId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int personId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Person p = ss.get(Person.class, personId);
			ss.delete(p);

			ss.getTransaction().commit();

			System.out.println("Delete person with ID: " + p.getId() + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete person failed!");
			e.printStackTrace();

			return false;
		}
	}
}
