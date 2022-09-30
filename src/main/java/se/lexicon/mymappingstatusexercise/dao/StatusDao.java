package se.lexicon.mymappingstatusexercise.dao;

import se.lexicon.mymappingstatusexercise.model.Status;

import java.util.List;

public interface StatusDao {

    Status save(Status status);
    Status findById(int statusId);
    List<Status> findAll();
    void remove(int statusId);
    Status update(Status status);
    List<Status> saveCars(List<Status> statusList);

}
