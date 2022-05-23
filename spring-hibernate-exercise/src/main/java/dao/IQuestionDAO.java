package dao;

import java.util.List;

import entity.Question;

public interface IQuestionDAO {
	public List<Question> select();

	public List<Question> selectRandom(String n, int level);

	public boolean insert(Question question);

	public boolean update(Question question);

	public Question detail(int question);

	public boolean delete(int question);
}
