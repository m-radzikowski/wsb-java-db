package orm.dao;

import orm.domain.Film;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class FilmDao extends Dao {

	public FilmDao(EntityManager em) {
		super(em);
	}

	public Film findWithId1() {
		return em
			.createQuery("FROM Film WHERE id = 1", Film.class)
			.getSingleResult();
	}

	public Film findByIdWithParameter(int id) {
		try {
			return em
				.createQuery("FROM Film WHERE id = :id", Film.class)
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Film findByIdWithEM(int id) {
		return em.find(Film.class, id);
	}

}
