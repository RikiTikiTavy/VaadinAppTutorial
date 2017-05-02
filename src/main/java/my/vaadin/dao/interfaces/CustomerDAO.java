package my.vaadin.dao.interfaces;

import java.util.List;

import my.vaadin.dao.objects.Customer;


public interface CustomerDAO {

	void insert(Customer customer);

	void delete(Long id);


	
	List<Customer>  updateCustomerList();
	
	void changeCustomer(Long id, String name, String position, String email);
}
