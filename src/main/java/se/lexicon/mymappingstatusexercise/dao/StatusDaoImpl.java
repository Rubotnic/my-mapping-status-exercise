package se.lexicon.mymappingstatusexercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mymappingstatusexercise.model.Status;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class StatusDaoImpl implements StatusDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Status save(Status status) {
        if (status == null) throw new IllegalArgumentException("Address was null");
        entityManager.persist(status);
        return status;
    }

    @Transactional
    @Override
    public Status findById(int statusId) {
        if (statusId <= 0) throw new IllegalArgumentException("Invalid ID");
        Status status = entityManager.find(Status.class, statusId);
        return Optional.ofNullable(status).get();
    }

    @Override
    public List<Status> findAll() {
        return entityManager.createQuery("SELECT s FROM Status s", Status.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void remove(int statusId) {
        entityManager.remove(findById(statusId));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Status update(Status status) {
        return entityManager.merge(status);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<Status> saveCars(List<Status> statusList) {
        statusList.forEach(this::save);
        return statusList;
    }
}