package pl.Dominik.dao;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import pl.Dominik.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public void add(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setRoles(Set.of("USER"));
        entityManager.persist(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT b FROM User b").getResultList();
    }


}
