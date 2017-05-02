package my.vaadin.dao.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {
	
	
	

	public Customer() {
		
	}

	public Customer(String firstName, String position, String email) {
		this.firstName = firstName;
		this.position = position;
		this.email = email;
	}

	private Long id;

	public String getCustomerId() {
		return id + "";
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



}