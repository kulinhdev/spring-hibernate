package dao;

import java.util.List;

import entity.Subject;

public interface ISubjectDAO {
	public List<Subject> select();

	public boolean insert(Subject subject);

	public List<Subject> search(String name);

	public boolean update(Subject subject);

	public Subject detail(int SubjectId);

	public boolean delete(int SubjectId);
}
