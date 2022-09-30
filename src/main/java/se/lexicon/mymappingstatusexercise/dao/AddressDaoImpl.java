package se.lexicon.mymappingstatusexercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mymappingstatusexercise.model.Address;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Address save(Address address) {
        if (address == null) throw new IllegalArgumentException("Address was null");
        entityManager.persist(address);
        return address;
    }

    @Transactional
    @Override
    public Optional<Address> findById(int addressId) {
        if (addressId <= 0) throw new IllegalArgumentException("Invalid ID");
        Address address = entityManager.find(Address.class, addressId);
        return Optional.ofNullable(address);
    }

    @Transactional
    @Override
    public void remove(Address address) {
        findById(address.getAddressId()).orElseThrow(() -> new IllegalArgumentException("Data not found"));
        entityManager.remove(address);
    }
    @Transactional
    @Override
    public Address update(Address address) {
        return entityManager.merge(address);
    }


    @Transactional
    @Override
    public List<Address> findAddressByStreet(String Street) {
        List<Address> foundByStreet = new ArrayList<>();
        TypedQuery<Address> query = entityManager.createQuery("SELECT s FROM m_address s WHERE s.street = :street", Address.class);
        query.setParameter("street", Street);
        foundByStreet = query.getResultList();
        return foundByStreet;
    }

    @Transactional
    @Override
    public List<Address> findAdressByZipCode(String zipCode){
        List<Address> foundAddresses = new ArrayList<>();
        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM m_address a WHERE a.zipCode = :zc", Address.class);
        query.setParameter("zc", zipCode);
        foundAddresses = query.getResultList();
        return foundAddresses;
    }
}