package jdbc.dao;

import jdbc.domain.JdbcFilm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcFilmDao extends JdbcDao {

	private final PreparedStatement getFilmByIdStmt;

	public JdbcFilmDao(Connection connection) throws SQLException {
		super(connection);

		getFilmByIdStmt = connection.prepareStatement("SELECT film_id, title, rental_rate, rental_duration FROM film WHERE film_id = ?");
	}

	public JdbcFilm findById(int searchId) throws SQLException {
		ResultSet filmResult = statement.executeQuery("SELECT film_id, title, rental_rate, rental_duration FROM film WHERE film_id = " + searchId);

		boolean filmExists = filmResult.first();
		if (filmExists) {
			int filmId = filmResult.getInt("film_id");
			String title = filmResult.getString("title");
			double rentalRate = filmResult.getDouble("rental_rate");
			int rentalDuration = filmResult.getInt("rental_duration");
			return new JdbcFilm(filmId, title, rentalRate, rentalDuration);
		} else {
			return null;
		}
	}

	public JdbcFilm findByIdWithPreparedStatement(int searchId) throws SQLException {
		getFilmByIdStmt.setInt(1, searchId);
		ResultSet filmResult = getFilmByIdStmt.executeQuery();

		boolean filmExists = filmResult.first();
		if (filmExists) {
			int filmId = filmResult.getInt("film_id");
			String title = filmResult.getString("title");
			double rentalRate = filmResult.getDouble("rental_rate");
			int rentalDuration = filmResult.getInt("rental_duration");
			return new JdbcFilm(filmId, title, rentalRate, rentalDuration);
		} else {
			return null;
		}
	}

}
