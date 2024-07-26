package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.entity.User;

import java.util.List;

public class UserDao extends BaseDao<User, Integer> {

    public UserDao() {
        super(User.class);
    }

    /**
     * Find users by a specific username.
     * @param username The username to search for.
     * @return A list of users that match the username.
     */
    public List<User> findByUsername(String username) {
        return searchByExactMatch(username,"username");
    }
}
