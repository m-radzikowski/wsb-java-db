package jdbc.dao;

import jdbc.domain.JdbcFilm;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFilmDao extends JdbcDao {

	public JdbcFilmDao(Connection connection) throws SQLException {
		super(connection);
	}

	public JdbcFilm findById(int searchId) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public JdbcFilm findByIdWithPreparedStatement(int searchId) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
