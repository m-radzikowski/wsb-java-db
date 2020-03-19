package orm.dao;

import orm.domain.Film;

import javax.persistence.EntityManager;

public class FilmDao extends Dao {

	public FilmDao(EntityManager em) {
		super(em);
	}

	public Film findWithId1() {
		throw new UnsupportedOperationException();
	}

	public Film findByIdWithQuery(int id) {
		throw new UnsupportedOperationException();
	}

	public Film findByIdWithNamedQuery(int id) {
		throw new UnsupportedOperationException();
	}

	public Film findByIdWithEM(int id) {
		throw new UnsupportedOperationException();
	}

}
