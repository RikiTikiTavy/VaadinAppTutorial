package my.vaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<>(Customer.class);
	private TextField filterText = new TextField();
	private Customer customer;

	private CustomerForm form = new CustomerForm(this);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		
		
		
		filterText.setPlaceholder("search");
		filterText.addValueChangeListener(e -> updateList());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);

		Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addClickListener(e -> filterText.clear());

		
		CssLayout search = new CssLayout();
		search.addComponents(filterText, clearFilterTextBtn);
		search.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		Button addCustomerBtn = new Button(VaadinIcons.PLUS);
		addCustomerBtn.setDescription("Add a new customer");
		addCustomerBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			form.setCustomer(new Customer());
			});
		
		Button editCustomer = new Button(VaadinIcons.ITALIC);
		editCustomer.setDescription("edit customer");
		
		Button delete = new Button(VaadinIcons.MINUS);
		delete.setDescription("Delete");
		delete.addClickListener(e -> this.delete());
		
		
		
		
		HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn, editCustomer, delete, search);
		grid.setColumns("customerId", "firstName", "position", "email");
		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);
		layout.addComponents(toolbar, main);
	
		updateList();
		setContent(layout);
		form.setVisible(false);
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() == null) {
				form.setVisible(false);
			} else {
				form.setCustomer(event.getValue());
			}
		});
	}

	private void delete() {
		service.delete(customer);
		this.updateList();
		setVisible(false);
	}
	
	
	
	public void updateList() {
		List<Customer> customers = service.findAll(filterText.getValue());
		grid.setItems(customers);
	}
	


	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
