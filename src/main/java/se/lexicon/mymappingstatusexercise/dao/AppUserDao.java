package se.lexicon.mymappingstatusexercise.dao;

import se.lexicon.mymappingstatusexercise.model.AppUser;
import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    AppUser save(AppUser appUser);
    Optional<AppUser> findById(int id);
    List<AppUser> findAll();
    void remove(AppUser appUser);
    AppUser update(AppUser appUser);
    List<AppUser> saveAppUsers(List<AppUser> appUsers);

}
