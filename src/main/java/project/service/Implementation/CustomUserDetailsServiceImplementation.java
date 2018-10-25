package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.repositories.UserRepository;

import org.springframework.security.core.AuthenticationException;
import project.service.CustomUserDetailsService;

import java.util.*;

@Service
public class CustomUserDetailsServiceImplementation implements CustomUserDetailsService {


    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    // Dependency Injection
    @Autowired
    public CustomUserDetailsServiceImplementation(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }


    public List<User> findAll() {
        return repository.findAll();
    }


    @Override
    public User findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

}