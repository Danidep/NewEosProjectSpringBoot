package it.eos.springuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class UserEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "MAIL")
	private String mail;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name ="NAME")
	private String name;

	@Column(name ="ACTIVE")
	private boolean active;

	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", mail='" + mail + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", active=" + active +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id == that.id &&
				Objects.equals(mail, that.mail) &&
				Objects.equals(password, that.password) &&
				Objects.equals(name, that.name) &&
				Objects.equals(active, that.active);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mail, password, name, active);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

