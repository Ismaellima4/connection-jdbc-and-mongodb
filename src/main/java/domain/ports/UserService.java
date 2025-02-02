package domain.ports;

import domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService<P> {
    Optional<User<P>> findById(P id);
    void save(User<P> value);
    void update(P id, User<P> value);
    void delete(P id);
    List<User<P>> findAll();
}
