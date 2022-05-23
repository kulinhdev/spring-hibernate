package entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Filed content isn't empty!")
	@Column(name = "content")
	private String content;

	@NotNull(message = "Field status is required!")
	@Column(name = "status")
	private boolean status;

	@ManyToOne
	@NotNull(message = "Field level class is required!")
	@JoinColumn(name = "level_id", referencedColumnName = "id")
	private QuestionLevel questionLevel;

	@ManyToOne
	@NotNull(message = "Field subject class is required!")
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject questionSubject;

	@OneToMany(mappedBy = "answersQuestion", fetch = FetchType.EAGER) // fetch = FetchType.EAGER
	private List<Answer> listAnswers;

	@ManyToMany(mappedBy = "listQuestions", fetch = FetchType.EAGER)
	private Set<Test> listTests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public QuestionLevel getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(QuestionLevel questionLevel) {
		this.questionLevel = questionLevel;
	}

	public Subject getQuestionSubject() {
		return questionSubject;
	}

	public void setQuestionSubject(Subject questionSubject) {
		this.questionSubject = questionSubject;
	}

	public List<Answer> getListAnswers() {
		return listAnswers;
	}

	public void setListAnswers(List<Answer> listAnswers) {
		this.listAnswers = listAnswers;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", status=" + status + ", questionLevel="
				+ questionLevel;
	}

}
