package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStoreDao extends JdbcDao {

	public JdbcStoreDao(Connection connection) throws SQLException {
		super(connection);
	}

	public List<String> list() throws SQLException {
		ResultSet storesResult = statement.executeQuery("SELECT store_id, address_id FROM store");

		List<String> stores = new ArrayList<>();
		while (storesResult.next()) {
			int id = storesResult.getInt("store_id");
			int addressId = storesResult.getInt("address_id");
			stores.add("Store id: " + id + ", Address id: " + addressId);
		}

		return stores;
	}

	public List<String> listWithAddresses() throws SQLException {
		ResultSet storesResult = statement.executeQuery(
			"SELECT s.store_id, s.address_id, a.address, a.district FROM store s " +
				"LEFT JOIN address a ON s.address_id = a.address_id"
		);

		List<String> stores = new ArrayList<>();
		while (storesResult.next()) {
			int id = storesResult.getInt("s.store_id");
			String address = storesResult.getString("a.address");
			String district = storesResult.getString("a.district");
			stores.add("Store id: " + id + ", Address: " + address + ", " + district);
		}

		return stores;
	}

	public List<String> listWithFullAddresses() throws SQLException {
		ResultSet storesResult = statement.executeQuery(
			"SELECT s.store_id, s.address_id, a.address, a.district, ci.city, co.country FROM store s " +
				"LEFT JOIN address a ON s.address_id = a.address_id " +
				"LEFT JOIN city ci ON a.city_id = ci.city_id " +
				"LEFT JOIN country co ON ci.country_id = co.country_id"
		);

		List<String> stores = new ArrayList<>();
		while (storesResult.next()) {
			int id = storesResult.getInt("s.store_id");
			String address = storesResult.getString("a.address");
			String district = storesResult.getString("a.district");
			String city = storesResult.getString("ci.city");
			String country = storesResult.getString("co.country");
			stores.add("Store id: " + id + ", Address: " + address + ", " + district + ", " + city + ", " + country);
		}

		return stores;
	}

}
