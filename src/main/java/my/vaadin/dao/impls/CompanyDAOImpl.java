package my.vaadin.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import my.vaadin.dao.interfaces.CompanyDAO;
import my.vaadin.dao.objects.Company;


public class CompanyDAOImpl implements  CompanyDAO{
	private JdbcTemplate jdbcTemplate;
	
	public CompanyDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}

	@Override
	public List<Company> updateCompanyList() {
		String sql = "SELECT * FROM Companies";

		return jdbcTemplate.query(sql, new CompanyRowMapper());
	}
	
	
	private static final class CompanyRowMapper implements RowMapper<Company> {

		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company company = new Company();
			company.setCompanyName(rs.getString("companyName"));
			company.setCompanyId(rs.getLong("companyId"));
			return company;
		}

	}
}
