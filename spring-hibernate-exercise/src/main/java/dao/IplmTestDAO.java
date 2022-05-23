package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Test;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class IplmTestDAO implements ITestDAO {
	@Override
	public List<Test> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Test> listTests = ss.createQuery("from Test").list();

			// Close connect
			ss.close();

			return listTests;

		} catch (Exception e) {
			System.out.println("Get tests failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Test test) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(test);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create test failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(Test test) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(test);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update test failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Test detail(int testId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Test test = (Test) ss.createQuery("from Test where id = :testId").setParameter("testId", testId)
					.getSingleResult();

			ss.close();

			return test;
		} catch (Exception e) {
			System.out.println("Get question width Id " + testId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int testId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Test test = ss.get(Test.class, testId);
			ss.delete(test);

			ss.getTransaction().commit();

			System.out.println("Delete test success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete test failed!");
			e.printStackTrace();

			return false;
		}
	}

}
