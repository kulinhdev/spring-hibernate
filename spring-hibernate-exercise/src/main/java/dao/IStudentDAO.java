package dao;

import java.util.List;

import entity.Student;

public interface IStudentDAO {
	public List<Student> select();

	public boolean insert(Student student);

	public List<Student> search(String name);

	public boolean update(Student student);

	public Student detail(int studentId);

	public boolean delete(int studentId);
}
