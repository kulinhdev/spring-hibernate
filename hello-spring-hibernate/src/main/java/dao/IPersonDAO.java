package dao;

import java.util.List;

import entity.Person;

public interface IPersonDAO {
	public List<Person> select();

	public boolean insert(Person p);

	public List<Person> search(String name);

	public boolean update(Person p);

	public Person detail(int personId);

	public boolean delete(int personId);
}
