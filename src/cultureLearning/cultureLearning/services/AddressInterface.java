package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Address;

public interface AddressInterface {
	public void registerAddress(Address u);
	public void deleteAddress(Address u);
	public List<Address>findAllAddress();
	public void update(Address u);
	public Address findById(String  code);
}
