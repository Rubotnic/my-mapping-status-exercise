package se.lexicon.mymappingstatusexercise.dao;

import se.lexicon.mymappingstatusexercise.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressDao {

    Address save(Address address);
    Optional<Address> findById(int addressId);
    void remove(Address address);
    Address update(Address address);

    // Extra
    List<Address> findAdressByZipCode(String zipCode);

    List<Address> findAddressByStreet(String street);

}
