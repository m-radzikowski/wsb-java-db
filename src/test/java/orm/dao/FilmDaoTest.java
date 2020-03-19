package orm.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import orm.dao.FilmDao;
import orm.domain.Film;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilmDaoTest {

	static EntityManager em;
	static FilmDao filmDao;

	@BeforeAll
	static void beforeAll() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu1");
		em = entityManagerFactory.createEntityManager();

		filmDao = new FilmDao(em);
	}

	@AfterAll
	static void afterAll() {
		em.close();
	}

	@Test
	void findWithId1Test() {
		Film film = filmDao.findWithId1();

		System.out.println(film);

		assertEquals(1, film.getFilmId());
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	void findByIdWithQuery() {
		Film film = filmDao.findByIdWithQuery(2);

		System.out.println(film);

		assertEquals(2, film.getFilmId());
		assertEquals("ACE GOLDFINGER", film.getTitle());
	}

	@Test
	void findByIdWithNamedQuery() {
		Film film = filmDao.findByIdWithNamedQuery(3);

		System.out.println(film);

		assertEquals(3, film.getFilmId());
		assertEquals("ADAPTATION HOLES", film.getTitle());
	}

	@Test
	void findByIdWithNamedQuery_NonExisting() {
		Film film = filmDao.findByIdWithNamedQuery(10_000);

		System.out.println(film);

		assertNull(film);
	}

	@Test
	void findByIdWithEM() {
		Film film = filmDao.findByIdWithEM(4);

		System.out.println(film);

		assertEquals(4, film.getFilmId());
		assertEquals("AFFAIR PREJUDICE", film.getTitle());
	}

	@Test
	void findByIdWithEM_NonExisting() {
		Film film = filmDao.findByIdWithEM(10_000);

		System.out.println(film);

		assertNull(film);
	}

}
