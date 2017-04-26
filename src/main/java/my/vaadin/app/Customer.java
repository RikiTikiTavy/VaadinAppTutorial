package my.vaadin.app;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {

	private Long id;

	private String customerId = "";

	public String getCustomerId() {
		return id + "";
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	private String firstName = "";

	private String position = "";




	private String email = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Customer && obj.getClass().equals(getClass())) {
			return this.id.equals(((Customer) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Customer clone() throws CloneNotSupportedException {
		return (Customer) super.clone();
	}

	@Override
	public String toString() {
		return firstName + " " + position;
	}
}