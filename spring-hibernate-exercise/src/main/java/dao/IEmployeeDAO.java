package dao;

import java.util.List;

import entity.Employee;

public interface IEmployeeDAO {
	public List<Employee> select();

	public boolean insert(Employee e);

	public List<Employee> search(String name);

	public boolean update(Employee e);

	public Employee detail(int employeeId);

	public boolean delete(int employeeId);
}
