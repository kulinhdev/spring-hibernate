package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.EmployeeRole;
import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class IplmRoleDAO implements IEmployeeRoleDAO {
	@Override
	public List<EmployeeRole> select() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session ss = sf.openSession();

			// Start Transaction
			ss.beginTransaction();

			List<EmployeeRole> listRoles = ss.createQuery("from EmployeeRole").list();

			// Close connect
			ss.close();

			return listRoles;

		} catch (Exception e) {
			System.out.println("Get roles failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean insert(EmployeeRole c) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.save(c);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Create role failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean update(EmployeeRole r) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			ss.update(r);

			ss.getTransaction().commit();
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Update role failed!");
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public EmployeeRole detail(int roleId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			EmployeeRole r = (EmployeeRole) ss.createQuery("from EmployeeRole where id = :roleId")
					.setParameter("roleId", roleId).getSingleResult();

			ss.close();

			return r;
		} catch (Exception e) {
			System.out.println("Get role width Id " + roleId + " failed!");
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean delete(int roleId) {
		try {
			Session ss = HibernateUtil.getSessionFactory().openSession();

			// Start Transaction
			ss.beginTransaction();
			EmployeeRole r = ss.get(EmployeeRole.class, roleId);
			ss.delete(r);

			ss.getTransaction().commit();

			System.out.println("Delete role with ID: " + r.getId() + " success !");
			ss.close();

			return true;
		} catch (Exception e) {
			System.out.println("Delete role failed!");
			e.printStackTrace();

			return false;
		}
	}

}
