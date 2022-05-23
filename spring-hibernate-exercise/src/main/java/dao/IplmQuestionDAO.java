package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Question;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class IplmQuestionDAO implements IQuestionDAO {
	@Override
	public List<Question> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Question> listQuestions = ss.createQuery("from Question").list();

			// Close connect
			ss.close();

			return listQuestions;

		} catch (Exception e) {
			System.out.println("Get questions failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Question question) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(question);

//			int generatedId = (Integer) 
//			System.out.println("generatedId ===> " + generatedId);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create question failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(Question question) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(question);

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
	public Question detail(int questionId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Question Question = (Question) ss.createQuery("from Question where id = :questionId")
					.setParameter("questionId", questionId).getSingleResult();

			ss.close();

			return Question;
		} catch (Exception e) {
			System.out.println("Get question width Id " + questionId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int questionId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Question question = ss.get(Question.class, questionId);
			ss.delete(question);

			ss.getTransaction().commit();

			System.out.println("Delete question with ID: " + questionId + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete question failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Question> selectRandom(String record, int level) {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Question> listQuestions = ss.createQuery("from Question order by newid()")
					.setMaxResults(Integer.parseInt(record)).getResultList();

			// Close connect
			ss.close();

			return listQuestions;

		} catch (Exception e) {
			System.out.println("Get questions failed!");
			e.printStackTrace();

			return null;
		}
	}

}
