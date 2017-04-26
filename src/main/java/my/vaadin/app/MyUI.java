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
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
public class MyUI extends UI {
	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<>(Customer.class);
	private TextField filterText = new TextField();
	private Button editCustomer;
	
	private CustomerForm form = new CustomerForm(this);
	
	public Button getEditCustomer() {
		return editCustomer;
	}

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

				
		editCustomer = new Button(VaadinIcons.ITALIC);
		editCustomer.setDescription("edit customer");
		editCustomer.addClickListener(e -> {
			grid.asSingleSelect();
			form.setCustomer(new Customer());
			});
		
		
		
		Button addCustomerBtn = new Button(VaadinIcons.PLUS);
		addCustomerBtn.setDescription("Add a new customer");
		addCustomerBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			form.getDelete().setEnabled(false);
			editCustomer.setEnabled(false);
			form.setCustomer(new Customer());
			});

		
		
	
		HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn, editCustomer, form.getDelete(), search);
		toolbar.setComponentAlignment(search, Alignment.BOTTOM_CENTER);
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
				//form.setVisible(false);
			} else {
				//form.setCustomer(event.getValue());
			}
		});
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
