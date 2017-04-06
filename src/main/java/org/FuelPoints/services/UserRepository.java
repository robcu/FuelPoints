package org.FuelPoints.services;

import org.FuelPoints.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String username);
    User findById(Integer id);
}
