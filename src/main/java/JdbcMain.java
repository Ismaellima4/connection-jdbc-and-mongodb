import domain.adapters.UserServiceImpl;
import domain.entities.User;
import domain.ports.UserService;
import infrastructure.JdbcUserRepositoryImpl;

public class JdbcMain {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db2";
        String username = "default";
        String password = "secret";

        UserService<Long> userService = new UserServiceImpl<>(new JdbcUserRepositoryImpl(jdbcUrl, username, password));

        User<Long> user = new User<>("John doe");
        userService.save(user);

        User<Long> userReceived = userService.findById(1L).get();
        System.out.println(userReceived);

        User<Long> userUpdate = new User<>("John doe update");
        userService.update(userReceived.getId(), userUpdate);

        System.out.println(userService.findById(userReceived.getId()));

        userService.delete(userReceived.getId());

        System.out.println(userService.findById(userReceived.getId()));
    }
}
