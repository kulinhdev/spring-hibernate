package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Test")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Filed name isn't empty!")
	@Column(name = "title")
	private String title;

	@NotNull(message = "Field status is required!")
	@Column(name = "status")
	private boolean status;

	@NotNull(message = "Field time is required!")
	@Column(name = "woking_time")
	private int woking_time;

	@ManyToOne
	@NotNull(message = "Field class is required!")
	@JoinColumn(name = "class_id", referencedColumnName = "id")
	private Class testClass;

	@ManyToOne
	@NotNull(message = "Field subject class is required!")
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject testSubject;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TestQuestion", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	Set<Question> listQuestions;

	public Test() {
	}

	public Test(int id, String title, boolean status, int time) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.woking_time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Class getTestClass() {
		return testClass;
	}

	public void setTestClass(Class testClass) {
		this.testClass = testClass;
	}

	public Subject getTestSubject() {
		return testSubject;
	}

	public void setTestSubject(Subject testSubject) {
		this.testSubject = testSubject;
	}

	public int getWoking_time() {
		return woking_time;
	}

	public void setWoking_time(int woking_time) {
		this.woking_time = woking_time;
	}

	public Set<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(Set<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", title=" + title + ", status=" + status + ", woking_time=" + woking_time + "]";
	}

}
