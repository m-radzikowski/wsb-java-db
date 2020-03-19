package orm.dao;

import orm.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;

public class StoreDao extends Dao {

	public StoreDao(EntityManager em) {
		super(em);
	}

	public Store findById(int id) {
		return em.find(Store.class, id);
	}

	public List<Store> findAll() {
		return em
			.createQuery(
				"FROM Store s " +
					"JOIN FETCH s.address a " +
					"JOIN FETCH a.city",
				Store.class)
			.getResultList();
	}

}
