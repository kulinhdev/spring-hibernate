package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Class;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class ImplClassDAO implements IClassDAO {

	@Override
	public List<Class> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Class> listPeople = ss.createQuery("from Class").list();

			// Close connect
			ss.close();

			return listPeople;

		} catch (Exception e) {
			System.out.println("Get classes failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Class c) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(c);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create class failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Class> search(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Class c) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(c);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update class failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Class detail(int classId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Class c = (Class) ss.createQuery("from Class where id = :classId").setParameter("classId", classId)
					.getSingleResult();

			ss.close();

			return c;
		} catch (Exception e) {
			System.out.println("Get class width Id " + classId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int classId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Class c = ss.get(Class.class, classId);
			ss.delete(c);

			ss.getTransaction().commit();

			System.out.println("Delete class with ID: " + c.getId() + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete class failed!");
			e.printStackTrace();

			return false;
		}
	}

}
