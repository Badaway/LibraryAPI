//package com.example.LibraryAPI.seed;
//
//import com.example.LibraryAPI.Dto.RegisterUserDto;
//import com.example.LibraryAPI.enums.RoleEnum;
//import com.example.LibraryAPI.model.Role;
//import com.example.LibraryAPI.model.User;
//import com.example.LibraryAPI.repository.RoleRepository;
//import com.example.LibraryAPI.repository.UserRepository;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Component
//public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//
//    public AdminSeeder(
//            RoleRepository roleRepository,
//            UserRepository  userRepository,
//            PasswordEncoder passwordEncoder
//    ) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        this.createAdministrator();
//    }
//
//    private void createAdministrator() {
//        RegisterUserDto userDto = new RegisterUserDto();
//        userDto.setName("Admin").setEmail("admin.admin@email.com").setPassword("123456");
//
//        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN);
//        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
//
//        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
//            return;
//        }
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(optionalRole.get());
//
//        var user = new User()
//                .setName(userDto.getName())
//                .setEmail(userDto.getEmail())
//                .setPassword(passwordEncoder.encode(userDto.getPassword()))
//                .setRoles(roleSet);
//
//        userRepository.save(user);
//    }
//
//}
