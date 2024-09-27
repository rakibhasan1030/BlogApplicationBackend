package org.rakibhasan.blog;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Role;
import org.rakibhasan.blog.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.rakibhasan.blog.config.AppConstants.*;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public BlogApplication(
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            Role roleAdmin = new Role();
            roleAdmin.setId(ID_ADMIN_USER);
            roleAdmin.setName(NAME_ADMIN_USER);

            Role roleNormal = new Role();
            roleNormal.setId(ID_NORMAL_USER);
            roleNormal.setName(NAME_NORMAL_USER);

            List<Role> roles = List.of(roleAdmin, roleNormal);
            List<Role> resule = this.roleRepository.saveAll(roles);

            resule.forEach(role -> {
                System.out.println(role.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
