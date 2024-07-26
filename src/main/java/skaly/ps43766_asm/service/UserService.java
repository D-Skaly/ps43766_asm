package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.User;
import skaly.ps43766_asm.repository.UserDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService extends BaseService<User, Integer> {

    public UserService() {
        super(new UserDao());
    }

    // Các phương thức CRUD kế thừa từ BaseService

    /**
     * Find users by username.
     * @param username The username to search for.
     * @return A list of users with the specified username.
     */
    public List<User> findByUsername(String username) {
        return ((UserDao) dao).findByUsername(username);
    }

    // Các phương thức phân trang, tìm kiếm, và lọc kế thừa từ BaseService
}
