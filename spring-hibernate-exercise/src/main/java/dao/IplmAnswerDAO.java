package dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Answer;
import entity.Question;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class IplmAnswerDAO implements IAnswerDAO {
	@Override
	public List<Answer> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Answer> listAnswers = ss.createQuery("from Answer").list();

			// Close connect
			ss.close();

			return listAnswers;

		} catch (Exception e) {
			System.out.println("Get answers failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Answer answer) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(answer);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create answer failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(Answer answer) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(answer);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update answer failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Answer detail(int answerId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Answer answer = (Answer) ss.createQuery("from Answer where id = :answerId")
					.setParameter("answerId", answerId).getSingleResult();

			ss.close();

			return answer;
		} catch (Exception e) {
			System.out.println("Get question width Id " + answerId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean deleteByQuestionId(int questionId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			int queryDelete = ss.createQuery("delete Answer where question_id = :questionId")
					.setParameter("questionId", questionId).executeUpdate();

			ss.getTransaction().commit();

			System.out.println("Delete answer with question Id: " + questionId + " success !");
			ss.close();

			return queryDelete > 0;
		} catch (Exception e) {
			System.out.println("Delete answer failed!");
			e.printStackTrace();

			return false;
		}
	}

}
