package cultureLearning.cultureLearning.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Address;

import cultureLearning.cultureLearning.services.impl.AddressServiceImplementation;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AddressModel {
	@WireVariable("address")
	private AddressServiceImplementation address;
	
	@Autowired
	private  Address add;
	private int id;
	private ListModelList<Address>listofCountries;

	@Init()
public void initialization() {
	
	add=new Address();
	listofCountries=new ListModelList<Address>(address.findAllAddress());
}
	@Command("saveAddress")
	@NotifyChange("listofCountries")
	public void addcountry() {
		try {
			
			address.registerAddress(add);
			Clients.showNotification("Saved!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Clients.showNotification("not saved"+e.getMessage());
		}
		
	}
	@Command("updateAddress")
	@NotifyChange("listofCountries")
	public void Updatecountry(@BindingParam("up")Address add) {
		try {
			address.update(add);
			Clients.showNotification("updated succsessful!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification(" fail to update"+e.getMessage());
		}
		
	}
		
	
	
	@Command("delete")
	@NotifyChange("listofCountries")
	public void deleteAddress(@BindingParam("de")Address add) {
		try {
			address.deleteAddress(add);
			Clients.showNotification("Deleted");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("Not deleted !"+e.getMessage());
			e.printStackTrace();
		}
	}
	public AddressServiceImplementation getAddress() {
		return address;
	}
	public void setAddress(AddressServiceImplementation address) {
		this.address = address;
	}
	public Address getAdd() {
		return add;
	}
	public void setAdd(Address add) {
		this.add = add;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ListModelList<Address> getListofCountries() {
		return listofCountries;
	}
	public void setListofCountries(ListModelList<Address> listofCountries) {
		this.listofCountries = listofCountries;
	}
	
}
