package com.full.circle.registration.restjwtpostgres.dao;

import com.full.circle.registration.restjwtpostgres.model.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
