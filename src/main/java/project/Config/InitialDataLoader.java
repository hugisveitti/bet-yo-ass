package project.Config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;
import project.persistence.repositories.RoleRepository;
import project.persistence.entities.Role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class is only for setting up the Roles in the RoleRepository
 * Maybe delete later.... If we simply load these roles with sql directly
 */

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private RoleRepository roleRepository;


    @Autowired
    public InitialDataLoader(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

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
