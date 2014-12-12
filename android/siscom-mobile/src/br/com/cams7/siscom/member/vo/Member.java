package br.com.cams7.siscom.member.vo;

public class Member {

	private Long id;

	private String name;
	private String email;
	private String phoneNumber;

	private boolean active;

	public Member() {
		super();
	}

	public Member(Long id) {
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ",name=" + name + ", e-mail=" + email
				+ ", phone=" + phoneNumber + ", active=" + active + "]";
	}

}
