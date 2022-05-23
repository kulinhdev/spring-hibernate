package dao;

import java.util.List;

import entity.Answer;

public interface IAnswerDAO {
	public List<Answer> select();

	public boolean insert(Answer answer);

	public boolean update(Answer answer);

	public Answer detail(int answerId);

	public boolean deleteByQuestionId(int questionId);
}
