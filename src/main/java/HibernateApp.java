import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu1");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Book> result = entityManager.createQuery("from Book", Book.class).getResultList();
        for (Book book : result) {
            System.out.println("Book id = " + book.getId() + ": " + book.getTitle());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
