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

	private Button cancel = new Button("Отменить");

	private Customer customer;
	private MyUI myUI;
	private Binder<Customer> binder = new Binder<>(Customer.class);


	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout( cancel);
		addComponents(firstName, position, email, buttons);
	
		cancel.setStyleName(ValoTheme.BUTTON_PRIMARY);
		cancel.setClickShortcut(KeyCode.ENTER);
		binder.bindInstanceFields(this);

	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.setBean(customer);	
	}

	

}