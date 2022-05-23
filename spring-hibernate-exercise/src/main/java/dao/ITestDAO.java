package dao;

import java.util.List;

import entity.Test;

public interface ITestDAO {
	public List<Test> select();

	public boolean insert(Test test);

	public boolean update(Test test);

	public Test detail(int testId);

	public boolean delete(int testId);
}
