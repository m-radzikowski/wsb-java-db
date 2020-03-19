package orm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	private Integer addressId;

	private String address;

	private String district;

	@ManyToOne
	@JoinColumn(name = "city_id")
//	@Transient
	private City city;

	@Override
	public String toString() {
		String s = "Address [" + addressId + "] " + address + ", " + district;
		if (city != null) {
			s += "\n\t" + city;
		}
		return s;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
