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
@Table(name = "Class")
public class Class {
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

	@OneToMany(mappedBy = "studentClass")
	private Set<Student> listStudents;

	@OneToMany(mappedBy = "subjetClass")
	private Set<Subject> listSubjects;

	@ManyToOne
	@NotNull(message = "Field teacher class is required!")
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Employee classEmployee;

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

	public Set<Student> getListStudents() {
		return listStudents;
	}

	public void setListStudents(Set<Student> listStudents) {
		this.listStudents = listStudents;
	}

	public Set<Subject> getListSubjects() {
		return listSubjects;
	}

	public void setListSubjects(Set<Subject> listSubjects) {
		this.listSubjects = listSubjects;
	}

	public Employee getClassEmployee() {
		return classEmployee;
	}

	public void setClassEmployee(Employee classEmployee) {
		this.classEmployee = classEmployee;
	}

}
