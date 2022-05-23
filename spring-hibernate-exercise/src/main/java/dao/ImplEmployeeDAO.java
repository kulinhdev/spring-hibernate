package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Employee;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class ImplEmployeeDAO implements IEmployeeDAO {

	@Override
	public List<Employee> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<Employee> listEmployees = ss.createQuery("from Employee").list();

			// Close connect
			ss.close();

			return listEmployees;

		} catch (Exception e) {
			System.out.println("Get data employees failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(Employee employee) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(employee);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create employee failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<Employee> search(String name) {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			String HQL = "from Employee where id > 0 and";

			if (!name.isEmpty()) {
				HQL += " name like :searchName";
			}

			List<Employee> listEmployees = ss.createQuery(HQL).setParameter("searchName", "%" + name + "%").list();

			// Close connect
			ss.close();

			return listEmployees;

		} catch (Exception e) {
			System.out.println("Search employees failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean update(Employee employee) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(employee);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update employee failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Employee detail(int employeeId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Employee employee = (Employee) ss.createQuery("from Employee where id = :employeeId")
					.setParameter("employeeId", employeeId).getSingleResult();

			ss.close();

			return employee;
		} catch (Exception e) {
			System.out.println("Get employee width Id " + employeeId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int employeeId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			Employee employee = ss.get(Employee.class, employeeId);
			ss.delete(employee);

			ss.getTransaction().commit();

			System.out.println("Delete employee with ID: " + employee.getId() + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete employee failed!");
			e.printStackTrace();

			return false;
		}
	}

}
