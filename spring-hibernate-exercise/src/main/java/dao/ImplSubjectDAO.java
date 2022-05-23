package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Subject;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class ImplSubjectDAO implements ISubjectDAO {

	@Override
	public List<Subject> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Subject> listSubjects = ss.createQuery("from Subject").list();

			// Close connect
			ss.close();

			return listSubjects;

		} catch (Exception e) {
			System.out.println("Get subjects failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Subject subject) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(subject);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create subject failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Subject> search(String name) {
		return null;
	}

	@Override
	public boolean update(Subject subject) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(subject);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update Subject failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Subject detail(int SubjectId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Subject c = (Subject) ss.createQuery("from Subject where id = :SubjectId")
					.setParameter("SubjectId", SubjectId).getSingleResult();

			ss.close();

			return c;
		} catch (Exception e) {
			System.out.println("Get subject width Id " + SubjectId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int SubjectId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Subject c = ss.get(Subject.class, SubjectId);
			ss.delete(c);

			ss.getTransaction().commit();

			System.out.println("Delete subject with ID: " + c.getId() + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete subject failed!");
			e.printStackTrace();

			return false;
		}
	}

}
