package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.entities.Role;
import project.persistence.repositories.RoleRepository;
import project.persistence.repositories.UserRepository;
import project.service.UserService;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    // Dependency Injection
    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }


    public class UserExsitsException extends AuthenticationException {
        public UserExsitsException(final String msg){
            super(msg);
        }
    }

    public User save(User user) throws UserExsitsException {
        //check if username is already in database
        if(repository.findByUsername(user.getUsername()) != null){
            throw new UserExsitsException("Username taken");
        };

        System.out.println("user ssave" + user);
        System.out.println(user.getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return repository.findAll();
    }

    /*
    // Instance Variables
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Dependency Injection
    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    public class UserExsitsException extends AuthenticationException {
        public UserExsitsException(final String msg){
            super(msg);
        }
    }
    @Override
    public User save(User user) throws UserExsitsException {
        //check if username is already in database
        if(repository.findByUsername(user.getUsername()) != null){
            throw new UserExsitsException("Username taken");
        };

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllReverseOrder() {
        // Get all the Users
        List<User> users = repository.findAll();

        // Reverse the list
        Collections.reverse(users);

        return users;
    }

    @Override
    public User findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<GrantedAuthority> getAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    */
}
