package entity;

import java.util.Date;
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
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Filed name isn't empty!")
	@Size(min = 6, max = 30, message = "Required min 6 and max 30 character!")
	@Column(name = "name")
	private String name;

	@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@NotEmpty(message = "Field email isn't empty!")
	@Column(name = "email")
	private String email;

	@NotEmpty(message = "Field phone isn't empty!")
	@Size(min = 10, max = 15, message = "Must be a phone number!")
	@Column(name = "phone")
	private String phone;

//	@NotEmpty(message = "You must choose a image!")
	@Column(name = "avatar")
	private String avatar;

	@NotNull(message = "Field birthday is required!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;

	@NotNull(message = "Field status is required!")
	@Column(name = "status")
	private boolean status;

	@NotNull(message = "Field gender is required!")
	@Column(name = "gender")
	private boolean gender;

	@NotEmpty(message = "Field address isn't empty!")
	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "classEmployee")
	private Set<Class> listClasses;

	@ManyToOne
	@NotNull(message = "Field employee role is required!")
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private EmployeeRole employeeRole;

	public Employee() {
		super();
	}

	public Employee(int id, String name, String email, String phone, String avatar, Date birthday, boolean status,
			boolean gender, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.birthday = birthday;
		this.status = status;
		this.gender = gender;
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

//	public Set<Class> getListClasses() {
//		return listClasses;
//	}
//
//	public void setListClasses(Set<Class> listClasses) {
//		this.listClasses = listClasses;
//	}
}
