package edu.missouristate.repository;
import edu.missouristate.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    public Optional<User> findByEmail(String email);
}