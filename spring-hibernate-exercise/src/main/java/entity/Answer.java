package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Answer")
public class Answer {
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
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question answersQuestion;

	public Answer() {
	}

	public Answer(String content, boolean status) {
		this.content = content;
		this.status = status;
	}

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

	public Question getAnswersQuestion() {
		return answersQuestion;
	}

	public void setAnswersQuestion(Question answersQuestion) {
		this.answersQuestion = answersQuestion;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", status=" + status + ", answersQuestion="
				+ answersQuestion + "]";
	}

}
