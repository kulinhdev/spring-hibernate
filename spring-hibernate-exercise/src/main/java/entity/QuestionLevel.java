package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QuestionLevel")
public class QuestionLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Filed name isn't empty!")
	@Column(name = "name")
	private String name;

	@NotNull(message = "Field status is required!")
	@Column(name = "status")
	private boolean status;

	@OneToMany(mappedBy = "questionLevel")
	private Set<Question> listQuestions;

	public QuestionLevel() {
	}

	public QuestionLevel(int id, String name, boolean status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(Set<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

}
