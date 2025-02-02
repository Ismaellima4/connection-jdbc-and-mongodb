package domain.adapters;

import domain.entities.User;
import domain.ports.UserRepository;
import domain.ports.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl<P> implements UserService<P> {
    private final UserRepository<P> userRepository;

    public UserServiceImpl(UserRepository<P> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User<P>> findById(P id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void save(User<P> value) {
        this.userRepository.save(value);
    }

    @Override
    public void update(P id, User<P> value) {
        this.userRepository.update(id, value);
    }

    @Override
    public void delete(P id) {
        this.userRepository.delete(id);
    }

    @Override
    public List<User<P>> findAll() {
        return this.userRepository.findAll();
    }
}
