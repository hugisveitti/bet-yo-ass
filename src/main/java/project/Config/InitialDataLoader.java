package project.Config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;
import project.persistence.repositories.RoleRepository;
import project.persistence.repositories.UserRepository;
import project.persistence.entities.Role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;


        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");



        alreadySetup = true;
    }


    @Transactional
    private void createRoleIfNotFound(String name) {

        Role role = roleRepository.findByRole(name);
        if (role == null) {
            role = new Role(name);

            roleRepository.save(role);
        }

    }


}
