package my.vaadin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerService {

	private static CustomerService instance;
	private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());
	private DatabaseConnectuion databaseInstance = DatabaseConnectuion.getInstance();
	private final HashMap<Long, Customer> contacts = new HashMap<>();
	private long nextId = 0;

	private CustomerService() {
	}

	public static CustomerService getInstance() {
		if (instance == null) {
			instance = new CustomerService();
		}
		return instance;
	}


	public synchronized List<Customer> findAll() {
		return findAll(null);
	}


	public synchronized List<Customer> findAll(String stringFilter) {
		ArrayList<Customer> arrayList = new ArrayList<>();
		for (Customer contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				ex.printStackTrace();
			}
		}
		Collections.sort(arrayList, new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}


	public synchronized List<Customer> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Customer> arrayList = new ArrayList<>();
		for (Customer contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	public synchronized long count() {
		return contacts.size();
	}


	public synchronized void delete(Customer value) {
		contacts.remove(value.getId());
	}

	public synchronized void save(Customer entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"null customer");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Customer) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

}