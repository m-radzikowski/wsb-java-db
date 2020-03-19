package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcRentalDao extends JdbcDao {

	public JdbcRentalDao(Connection connection) throws SQLException {
		super(connection);
	}

	public int countByDay(LocalDate day) throws SQLException {
		ResultSet result = statement.executeQuery(
			"SELECT COUNT(*) AS rental_count, DATE(rental_date) AS rental_day " +
				"FROM rental " +
				"GROUP BY rental_day " +
				"HAVING rental_day = '" + day + "'");
		if (result.first()) {
			return result.getInt("rental_count");
		} else {
			return 0;
		}
	}

}
