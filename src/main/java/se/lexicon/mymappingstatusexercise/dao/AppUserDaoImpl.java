package se.lexicon.mymappingstatusexercise.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.mymappingstatusexercise.model.AppUser;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public AppUser save(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("Student was null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Transactional
    @Override
    public Optional<AppUser> findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        AppUser appUser = entityManager.find(AppUser.class, id);
        return Optional.ofNullable(appUser);
    }

    @Transactional
    @Override
    public List<AppUser> findAll() {
        Query selectQuery = entityManager.createQuery("SELECT s FROM AppUser s");
        return selectQuery.getResultList();
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void remove(AppUser appUser) {
        findById(appUser.getUserId()).orElseThrow(() -> new IllegalArgumentException("Data not found"));
        entityManager.remove(appUser);
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public List<AppUser> saveAppUsers(List<AppUser> appUsers) {
        appUsers.forEach(this::save);
        return appUsers;
    }
}
