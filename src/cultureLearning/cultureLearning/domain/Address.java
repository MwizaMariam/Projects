package cultureLearning.cultureLearning.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.mysql.jdbc.Driver;


@Entity
public class Address implements Serializable {

	@Id
	private String code;
	private String name;
	@OneToMany(mappedBy = "address")
	private List<User> user;
	
	
	/*
	 * public Address(String code, String name, List<User> user) { super();
	 * this.code = code; this.name = name; this.user = user; }
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return getName() ;
	}
	
	
	
	
}
