package org.hyperskill.Recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.hyperskill.Recipe.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public User findByEmail(String email);
}
