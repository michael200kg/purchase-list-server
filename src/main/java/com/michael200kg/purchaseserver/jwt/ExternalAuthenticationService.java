package com.michael200kg.purchaseserver.jwt;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExternalAuthenticationService implements AuthService {

    private final JdbcTemplate authJdbcTemplate;

    public ExternalAuthenticationService(
            JdbcTemplate authJdbcTemplate
    ) {
        this.authJdbcTemplate = authJdbcTemplate;
    }

    @Override
    public String getPassword(String username) throws UsernameNotFoundException {
        String password = null;
        try {
            password = authJdbcTemplate
                    .queryForObject(
                            "SELECT password from user where username=?",
                            String.class,
                            username
                    );
        } catch (Exception e) {
            // do nothing
        }
        return password;
    }

    @Override
    public boolean isUserExists(String username) throws UsernameNotFoundException {
        Integer cnt_ = 0;
        try {
            cnt_ = authJdbcTemplate
                    .queryForObject(
                            "SELECT count(*) from user where username=?",
                            Integer.class,
                            username
                    );
        } catch (Exception e) {
            // do nothing
        }
        return cnt_ > 0;
    }

    @Override
    public void createUser(String username, String password) throws UserAlreadyExistsException {
        try {
            this.authJdbcTemplate.update(
                    "insert into user (username, password) values (?,?)",
                    username,
                    password
            );
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException(username);
        }
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        this.authJdbcTemplate.update(
                "update user set password = ? where username = ?",
                newPassword,
                username
        );
        this.authJdbcTemplate.update("commit");
    }
}
