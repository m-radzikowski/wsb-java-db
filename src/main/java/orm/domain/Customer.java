package orm.domain;

import javax.persistence.*;

@Entity
public class Customer {

	@Id
	private Integer customerId;

	private String firstName;

	private String lastName;

	private String email;

	@OneToOne
	@JoinColumn(name = "address_id")
//	@Transient
	private Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
//	@Transient
	private Store store;

	@Override
	public String toString() {
		String s = "Customer [" + customerId + "] " + firstName + " " + lastName + " <" + email + ">";
		if (address != null) {
			s += "\n\t" + address;
		}
		return s;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
