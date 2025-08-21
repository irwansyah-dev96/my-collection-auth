package io.irwansyahdev96.readcollection.business.user.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.irwansyahdev96.readcollection.base.dao.BaseDao;
import io.irwansyahdev96.readcollection.business.user.model.User;



@Repository
public class UserDao extends BaseDao{

    public Optional<User> getByUsername(final String username) {
        final User result = getEM().createQuery("SELECT u FROM User u WHERE u.username = :username",User.class)
                .setParameter("username", username)
                .getSingleResult();

        final Optional<User> optional = Optional.ofNullable(result);

        return optional;
    }
}
