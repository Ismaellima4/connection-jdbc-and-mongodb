package infrastructure;

import domain.entities.User;
import domain.ports.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepositoryImpl implements UserRepository<Long> {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public JdbcUserRepositoryImpl(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<User<Long>> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tb_users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapEntityFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User<Long> value) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tb_users (username) VALUES (?)");
            statement.setString(1, value.getUserName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, User<Long> value) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE tb_users SET username = ? WHERE id = ?");
            statement.setString(1, value.getUserName());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password)) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tb_users WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User<Long>> findAll() {
        List<User<Long>> userList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password)) {
             PreparedStatement statement = connection.prepareStatement("SELECT id, username FROM tb_users");
             ResultSet resultSet =  statement.executeQuery();
             while (resultSet.next()){
                 userList.add(mapEntityFromResultSet(resultSet));
             }
             return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User<Long> mapEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User<Long> user = new User<>();
        user.setId(resultSet.getLong("id"));
        user.setUserName(resultSet.getString("username"));
        return user;
    }
}
