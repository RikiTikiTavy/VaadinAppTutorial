package my.vaadin.app;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import my.vaadin.dao.impls.CompanyDAOImpl;
import my.vaadin.dao.objects.Company;

public class CompanyLayout extends VerticalLayout {

	private SingleConnectionDataSource companyConnection = new SingleConnectionDataSource();
	private CompanyDAOImpl companyDAOImpl;
	private Grid<Company> grid = new Grid<>(Company.class);

	HorizontalLayout main = new HorizontalLayout(grid);

	public CompanyLayout() {

		companyConnection.setDriverClassName("com.mysql.jdbc.Driver");
		companyConnection.setUrl("jdbc:mysql://localhost/VaadinDB");
		companyConnection.setUsername("root");
		companyConnection.setPassword("");
		JdbcTemplate companyNpjt = new JdbcTemplate(companyConnection);
		companyDAOImpl = new CompanyDAOImpl(companyNpjt);

		addComponents(main);

		grid.setColumns("companyName");
		grid.setSizeFull();
		updateList();
	}

	public void updateList() {
		List<Company> customers = companyDAOImpl.updateCompanyList();
		grid.setItems(customers);
	}

}
