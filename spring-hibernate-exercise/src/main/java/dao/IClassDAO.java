package dao;

import java.util.List;
import entity.Class;

public interface IClassDAO {
	public List<Class> select();

	public boolean insert(Class c);

	public List<Class> search(String name);

	public boolean update(Class c);

	public Class detail(int classId);

	public boolean delete(int classId);
}
