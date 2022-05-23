package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.QuestionLevel;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class IplmLevelDAO implements ILevelDAO {
	@Override
	public List<QuestionLevel> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<QuestionLevel> listQuestionLevels = ss.createQuery("from QuestionLevel").list();

			// Close connect
			ss.close();

			return listQuestionLevels;

		} catch (Exception e) {
			System.out.println("Get levels failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(QuestionLevel QuestionLevel) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(QuestionLevel);

//			int generatedId = (Integer) 
//			System.out.println("generatedId ===> " + generatedId);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create level failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(QuestionLevel QuestionLevel) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(QuestionLevel);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update level failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public QuestionLevel detail(int levelId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			QuestionLevel QuestionLevel = (QuestionLevel) ss.createQuery("from QuestionLevel where id = :roleId")
					.setParameter("roleId", levelId).getSingleResult();

			ss.close();

			return QuestionLevel;
		} catch (Exception e) {
			System.out.println("Get level width Id " + levelId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int levelId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			QuestionLevel QuestionLevel = ss.get(QuestionLevel.class, levelId);
			ss.delete(QuestionLevel);

			ss.getTransaction().commit();

			System.out.println("Delete level with ID: " + levelId + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete level failed!");
			e.printStackTrace();

			return false;
		}
	}

}
