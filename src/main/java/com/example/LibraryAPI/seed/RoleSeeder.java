//package com.example.LibraryAPI.seed;
//
//import com.example.LibraryAPI.enums.RoleEnum;
//import com.example.LibraryAPI.model.Role;
//import com.example.LibraryAPI.repository.RoleRepository;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//@Configuration
//public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
//    private final RoleRepository roleRepository;
//
//
//    public RoleSeeder(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        this.loadRoles();
//    }
//
//    private void loadRoles() {
//        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.ROLE_USER, RoleEnum.ROLE_ADMIN};
//
//
//        Arrays.stream(roleNames).forEach((roleName) -> {
//            Optional<Role> optionalRole = roleRepository.findByName(roleName);
//
//            optionalRole.ifPresentOrElse(System.out::println, () -> {
//                Role roleToCreate = new Role();
//
//                roleToCreate.setName(roleName);
//
//                roleRepository.save(roleToCreate);
//            });
//        });
//    }
//}
