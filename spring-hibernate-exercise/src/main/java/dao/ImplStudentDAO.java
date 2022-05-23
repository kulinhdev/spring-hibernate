package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Student;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class ImplStudentDAO implements IStudentDAO {

	@Override
	public List<Student> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Student> listStudents = ss.createQuery("from Student").list();

			// Close connect
			ss.close();

			return listStudents;

		} catch (Exception e) {
			System.out.println("Get data students failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Student Student) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(Student);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create student failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Student> search(String name) {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			String HQL = "from Student where id > 0 and";

			if (!name.isEmpty()) {
				HQL += " name like :searchName";
			}

			List<Student> listStudents = ss.createQuery(HQL).setParameter("searchName", "%" + name + "%").list();

			// Close connect
			ss.close();

			return listStudents;

		} catch (Exception e) {
			System.out.println("Search students failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean update(Student Student) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(Student);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update student failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Student detail(int studentId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Student Student = (Student) ss.createQuery("from Student where id = :studentId")
					.setParameter("studentId", studentId).getSingleResult();

			ss.close();

			return Student;
		} catch (Exception e) {
			System.out.println("Get student width Id " + studentId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int StudentId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Student Student = ss.get(Student.class, StudentId);
			ss.delete(Student);

			ss.getTransaction().commit();

			System.out.println("Delete student with ID: " + StudentId + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete student failed!");
			e.printStackTrace();

			return false;
		}
	}

}
