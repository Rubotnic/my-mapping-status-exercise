package se.lexicon.mymappingstatusexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.lexicon.mymappingstatusexercise.dao.AddressDao;
import se.lexicon.mymappingstatusexercise.dao.AppUserDao;
import se.lexicon.mymappingstatusexercise.dao.CarDao;
import se.lexicon.mymappingstatusexercise.dao.StatusDao;
import se.lexicon.mymappingstatusexercise.model.Address;
import se.lexicon.mymappingstatusexercise.model.AppUser;
import se.lexicon.mymappingstatusexercise.model.Car;
import se.lexicon.mymappingstatusexercise.model.Status;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;


@Profile(value = "dev")
@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserDao appUserDao, CarDao carDao, AddressDao addressDao, StatusDao statusDao, EntityManager entityManager){

        this.addressDao = addressDao;
        this.carDao = carDao;
        this.appUserDao = appUserDao;
        this.statusDao = statusDao;
        this.entityManager = entityManager;

    }

    private final AddressDao addressDao;
    private final CarDao carDao;
    private final AppUserDao appUserDao;

    private final StatusDao statusDao;
    private final EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

// --- User 1

        AppUser appUserRager = new AppUser("rager@mail.com", "Råger Rågersson", "123456");
        entityManager.persist(appUserRager);
        System.out.println(appUserRager);

        appUserRager = appUserDao.save(appUserRager);

//--- Adress 1

        Address addressRager = new Address("Götteborg", "Gate 3", "212 12");
        entityManager.persist(addressRager);
        appUserRager.setAddress(addressRager);

        entityManager.flush();

        System.out.println("Råger = " + appUserRager);



// --- User 2


        AppUser appUserStefan = new AppUser("stefan@mail.com", "Stefan Stefansson", "qwerty");
        entityManager.persist(appUserStefan);
        System.out.println(appUserStefan);

        appUserStefan = appUserDao.save(appUserStefan);


//--- Adress 2

        Address addressStefan = new Address("Stökholm", "Gata 2", "313 12");
        entityManager.persist(addressStefan);
        appUserStefan.setAddress(addressStefan);

        entityManager.flush();

        System.out.println("Stefan = " + appUserStefan);


//--- Status 1

        Status ragerCars = new Status("All cars Råger owns");
        entityManager.persist(ragerCars);
        System.out.println(ragerCars);

//-- Status 2

        Status stefanCars = new Status("All cars Stefan owns");
        entityManager.persist(stefanCars);
        System.out.println(stefanCars);



        Car ragersCars = carDao.save(new Car("CCC123", "Mässla", "V2", LocalDate.parse("2022-12-12")));
        Car stefansCars = carDao.save(new Car("ABC888", "SAAB", "CD3", LocalDate.parse("2022-11-11")));



        appUserRager.addCar(ragersCars);
        appUserStefan.addCar(stefansCars);


        appUserRager.addCar(new Car("ABC777", "VOLVO", "HHD", LocalDate.parse("2022-10-10")));
        entityManager.flush();




    }
}