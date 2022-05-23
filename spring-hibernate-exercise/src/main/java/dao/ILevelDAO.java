package dao;

import java.util.List;

import entity.QuestionLevel;

public interface ILevelDAO {
	public List<QuestionLevel> select();

	public boolean insert(QuestionLevel QuestionLevel);

	public boolean update(QuestionLevel QuestionLevel);

	public QuestionLevel detail(int QuestionLevelId);

	public boolean delete(int QuestionLevelId);
}
