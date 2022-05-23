package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Filed name isn't empty!")
	@Size(min = 6, max = 30, message = "Required min 6 and max 30 character!")
	@Column(name = "name")
	private String name;

	@NotNull(message = "Field status is required!")
	@Column(name = "status")
	private boolean status;

	@NotEmpty(message = "Field description isn't empty!")
	@Column(name = "description")
	private String description;

	@ManyToOne(optional = false)
	@NotNull(message = "Field class is required!")
	@JoinColumn(name = "class_id", referencedColumnName = "id")
	private Class subjetClass;

	@OneToMany(mappedBy = "questionSubject")
	private Set<Question> listQuestions;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class getSubjetClass() {
		return subjetClass;
	}

	public void setSubjetClass(Class subjetClass) {
		this.subjetClass = subjetClass;
	}

	public Set<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(Set<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

}
