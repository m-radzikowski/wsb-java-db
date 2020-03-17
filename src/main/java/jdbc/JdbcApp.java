package jdbc;

import java.sql.*;
import java.time.LocalDate;

public class JdbcApp {

	public static void main(String[] args) throws SQLException {
		try (
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "qwerty");
			Statement statement = connection.createStatement()
		) {
			listStores(statement);
			listStoresWithAddresses(statement);
			listStoresWithFullAddresses(statement);

			JdbcFilm film1 = findFilm(statement, 1);
			System.out.println("Find film 1 result:");
			System.out.println(film1);
			System.out.println();

			JdbcFilm film10000 = findFilm(statement, 10_000);
			System.out.println("Find film 10.000 result:");
			System.out.println(film10000);
			System.out.println();

			PreparedStatement getFilmByIdStmt = connection.prepareStatement("SELECT film_id, title, rental_rate, rental_duration FROM Film WHERE film_id = ?");
			System.out.println("Films found with prepared statement:");
			JdbcFilm film10 = findFilmWithPreparedStatement(getFilmByIdStmt, 10);
			System.out.println(film10);
			JdbcFilm film11 = findFilmWithPreparedStatement(getFilmByIdStmt, 11);
			System.out.println(film11);
			System.out.println();

			LocalDate day = LocalDate.of(2005, 5, 30);
			int rentalsCount = countRentalsFromDay(statement, day);
			System.out.println("Rentals count from " + day + ": " + rentalsCount);

			JdbcActor tomHanks = new JdbcActor(1000, "TOM", "HANKS");
			insertActor(statement, tomHanks);
		}
	}

	private static void insertActor(Statement statement, JdbcActor actor) throws SQLException {
		statement.executeUpdate(
			"INSERT IGNORE INTO actor " +
				"(actor_id, first_name, last_name) " +
				"VALUES " +
				"(" + actor.getActorId() + ", '" + actor.getFirstName() + "', '" + actor.getLastName() + "')"
		);
	}

	private static void listStores(Statement statement) throws SQLException {
		ResultSet storesResult = statement.executeQuery("SELECT store_id, address_id FROM Store");

		System.out.println("Stores:");
		while (storesResult.next()) {
			int id = storesResult.getInt("store_id");
			int addressId = storesResult.getInt("address_id");
			System.out.println("[" + id + "] Address id: " + addressId);
		}

		System.out.println();
	}

	private static void listStoresWithAddresses(Statement statement) throws SQLException {
		ResultSet storesResult = statement.executeQuery(
			"SELECT s.store_id, s.address_id, a.address, a.district FROM Store s " +
				"LEFT JOIN Address a ON s.address_id = a.address_id"
		);

		System.out.println("Stores:");
		while (storesResult.next()) {
			int id = storesResult.getInt("s.store_id");
			String address = storesResult.getString("a.address");
			String district = storesResult.getString("a.district");
			System.out.println("[" + id + "] Address: " + address + ", " + district);
		}

		System.out.println();
	}

	private static void listStoresWithFullAddresses(Statement statement) throws SQLException {
		ResultSet storesResult = statement.executeQuery(
			"SELECT s.store_id, s.address_id, a.address, a.district, ci.city, co.country FROM Store s " +
				"LEFT JOIN Address a ON s.address_id = a.address_id " +
				"LEFT JOIN City ci ON a.city_id = ci.city_id " +
				"LEFT JOIN Country co ON ci.country_id = co.country_id"
		);

		System.out.println("Stores:");
		while (storesResult.next()) {
			int id = storesResult.getInt("s.store_id");
			String address = storesResult.getString("a.address");
			String district = storesResult.getString("a.district");
			String city = storesResult.getString("ci.city");
			String country = storesResult.getString("co.country");
			System.out.println("[" + id + "] Address: " + address + ", " + district + ", " + city + ", " + country);
		}

		System.out.println();
	}

	private static JdbcFilm findFilm(Statement statement, int searchId) throws SQLException {
		ResultSet filmResult = statement.executeQuery("SELECT film_id, title, rental_rate, rental_duration FROM Film WHERE film_id = " + searchId);

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

	private static JdbcFilm findFilmWithPreparedStatement(PreparedStatement getFilmByIdStmt, int searchId) throws SQLException {
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

	private static int countRentalsFromDay(Statement statement, LocalDate day) throws SQLException {
		ResultSet result = statement.executeQuery("SELECT COUNT(*) as rental_count, DATE(rental_date) as rental_day FROM rental GROUP BY rental_day HAVING rental_day = '" + day + "'");
		if (result.first()) {
			return result.getInt("rental_count");
		} else {
			return 0;
		}
	}

}
