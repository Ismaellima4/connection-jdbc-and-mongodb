import domain.adapters.UserServiceImpl;
import domain.entities.User;
import domain.ports.UserService;
import infrastructure.MongodbUserRepositoryImpl;
import org.bson.types.ObjectId;

import java.util.List;

public class MongodbMain {

    public static void main(String[] args) {
        String uri = "mongodb://user:secret@localhost:27017/";
        String databaseName = "db2";
        String collectionName = "users";

        UserService<ObjectId> userService = new UserServiceImpl<>(new MongodbUserRepositoryImpl(uri,databaseName, collectionName));

        User<ObjectId> user = new User<>("John doe");
        userService.save(user);
        List<User<ObjectId>> userList = userService.findAll();

        System.out.println(userService.findById(userList.getFirst().getId()));

        User<ObjectId> userUpdate = new User<>("John doe update");

        userService.update(userList.getFirst().getId(), userUpdate);
        System.out.println(userService.findById(userList.getFirst().getId()));

        userService.delete(userList.getFirst().getId());

        System.out.println(userService.findById(userList.getFirst().getId()));

    }
}
