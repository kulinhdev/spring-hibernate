package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "{form.empty}")
	@Size(min = 6, max = 30, message = "{form.strlength}")
	@Column(name = "name")
	private String name;

	// @NotEmpty(message = "{form.empty}")
	@Column(name = "avatar")
	private String avatar;

	// private MultipartFile fileAvatar;

	@NotNull(message = "{form.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	private boolean gender;

	@NotEmpty(message = "{form.empty}")
	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	public Person() {
	}

	public Person(String name, String avatar, Date birthday, boolean gender, String phone, String address) {
		super();
//		String id, 
//		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

//	public MultipartFile getFileAvatar() {
//		return fileAvatar;
//	}
//
//	public void setFileAvatar(MultipartFile fileAvatar) {
//		this.fileAvatar = fileAvatar;
//	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
