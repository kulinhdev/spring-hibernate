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
import javax.validation.constraints.Size;

@Entity
@Table(name = "EmployeeRole")
public class EmployeeRole {
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

	@OneToMany(mappedBy = "employeeRole")
	private Set<Employee> listEmployees;

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

	public Set<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(Set<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

}
