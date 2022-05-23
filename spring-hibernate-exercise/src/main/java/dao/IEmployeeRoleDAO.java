package dao;

import java.util.List;

import entity.EmployeeRole;

public interface IEmployeeRoleDAO {
	public List<EmployeeRole> select();

	public boolean insert(EmployeeRole c);

	public boolean update(EmployeeRole c);

	public EmployeeRole detail(int EmployeeRoleId);

	public boolean delete(int EmployeeRoleId);
}
