package it.eos.springuser.model;

import java.util.Objects;

public class UserModel {
	
	private long id;

	private String mail;

	private String password;

	private String name;

	private boolean active;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserModel userModel = (UserModel) o;
		return id == userModel.id &&
				Objects.equals(mail, userModel.mail) &&
				Objects.equals(password, userModel.password) &&
				Objects.equals(name, userModel.name) &&
				Objects.equals(active, userModel.active);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mail, password, name);
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"id=" + id +
				", mail='" + mail + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", active=" + active +
				'}';
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
