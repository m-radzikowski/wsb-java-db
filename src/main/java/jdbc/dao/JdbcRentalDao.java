package jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcRentalDao extends JdbcDao {

	public JdbcRentalDao(Connection connection) throws SQLException {
		super(connection);
	}

	public int countByDay(LocalDate day) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
