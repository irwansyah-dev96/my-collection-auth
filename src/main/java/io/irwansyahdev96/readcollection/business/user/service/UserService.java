package io.irwansyahdev96.readcollection.business.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.irwansyahdev96.readcollection.base.dto.res.BaseResListDto;
import io.irwansyahdev96.readcollection.business.user.dao.UserDao;
import io.irwansyahdev96.readcollection.business.user.dto.UserReqDto;
import io.irwansyahdev96.readcollection.business.user.model.User;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> save(UserReqDto userReq){
        User user = new User();
        user.setUsername(userReq.getUsername());
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        user.setRole(userReq.getRole());
        user.setName(userReq.getName());


        User userInsert = userDao.save(user);

        Map<String, Object> message = new HashMap<>();

        if(userInsert != null){
            message.put("id", userInsert.getUsername());
            message.put("message", "User has been added");
        }else{
            throw new RuntimeException("Failed to save");
        }

        return message;
    }

    public BaseResListDto<User> getAll(){
        BaseResListDto<User> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(userDao.getAll());
        baseResListDto.setCountOfData(userDao.count(User.class));

        return baseResListDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.getByUsername(username);

        if(user.isPresent()){
            return new org.springframework.security.core.userdetails.User(username,user.get().getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Username dan Password salah");
    }

    public Optional<User> getByUsername(String email) {
        return userDao.getByUsername(email);
    }
}
