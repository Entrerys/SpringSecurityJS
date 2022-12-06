package ru.mishucov.spring.springSecurityApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mishucov.spring.springSecurityApp.dao.UsersDao;
import ru.mishucov.spring.springSecurityApp.model.Role;
import ru.mishucov.spring.springSecurityApp.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersDao usersdao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersdao = usersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersdao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersdao.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return usersdao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        usersdao.updateUser(id, user);
    }

    @Transactional
    public void removeUserById(int id) {
        usersdao.removeUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return usersdao.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersdao.getUserByEmail(email);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersdao.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public void update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersdao.update(user);
    }

}
