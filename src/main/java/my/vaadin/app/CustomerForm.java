package my.vaadin.app;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class CustomerForm extends FormLayout {

	private TextField firstName = new TextField("First name");
	private TextField position = new TextField("Position");
	private TextField email = new TextField("Email");
	private Button save = new Button("Сохранить");
	private Button delete = new Button("Удалить");
	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;
	private Binder<Customer> binder = new Binder<>(Customer.class);

	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save);
		addComponents(firstName, position, email, buttons);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		binder.bindInstanceFields(this);
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
	}

	public Button getDelete() {
		return delete;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.setBean(customer);
	}


	private void delete() {
		service.delete(customer);
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(customer);
		myUI.updateList();
		delete.setEnabled(true);
		myUI.getEditCustomer().setEnabled(true);
		setVisible(false);
	}
}