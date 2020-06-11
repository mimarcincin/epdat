package sk.upjs.epdat.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
