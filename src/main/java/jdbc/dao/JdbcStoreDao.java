package jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcStoreDao extends JdbcDao {

	public JdbcStoreDao(Connection connection) throws SQLException {
		super(connection);
	}

	public List<String> list() throws SQLException {
		throw new UnsupportedOperationException();
	}

	public List<String> listWithAddresses() throws SQLException {
		throw new UnsupportedOperationException();
	}

	public List<String> listWithFullAddresses() throws SQLException {
		throw new UnsupportedOperationException();
	}

}
