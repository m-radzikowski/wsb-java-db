package orm.domain;

import javax.persistence.*;

@Entity
public class Store {

	@Id
	private Integer storeId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
//	@Transient // TODO Remove this annotation and replace with valid relation config annotations
	private Address address;

	@Override
	public String toString() {
		String s = "Store [" + storeId + "]";
		if (address != null) {
			s += "\n\t" + address;
		}
		return s;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
