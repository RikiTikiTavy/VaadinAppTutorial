package my.vaadin.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import my.vaadin.dao.interfaces.CustomerDAO;
import my.vaadin.dao.objects.Customer;


public class CustomerDAOImpl implements CustomerDAO {
	private JdbcTemplate jdbcTemplate;
	
	
	
	public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	@Override
	public void insert(Customer customer) {
		String sql = "insert into Customers (First_Name, Position, Email) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { customer.getFirstName(), customer.getPosition(), customer.getEmail()});	
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM Customers WHERE id=?";
		jdbcTemplate.update(sql, id);
	}
	


	@Override
	public List<Customer> updateCustomerList() {
		
		String sql = "SELECT * FROM Customers";

		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}
	
	
	private static final class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			 customer.setFirstName(rs.getString("First_Name"));
			 customer.setPosition(rs.getString("Position"));
			 customer.setEmail(rs.getString("Email"));
			 customer.setId(rs.getLong("id"));
			return customer;
		}

	}


	@Override
	public void changeCustomer(Long id, String name, String position, String email) {
		String sql = "UPDATE Customers SET First_Name = ?, Position = ?, Email = ?  WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { name, position, email});
		
	}


}
