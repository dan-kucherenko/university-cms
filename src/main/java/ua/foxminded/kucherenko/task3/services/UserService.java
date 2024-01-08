package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.dto.RegUserDto;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.models.UserEntity;
import ua.foxminded.kucherenko.task3.repositories.RoleRepository;
import ua.foxminded.kucherenko.task3.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Page<UserEntity> getAllUsers(int page, int size) {
        LOGGER.debug("Getting all the users");
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public void updateUserRole(int userId, Role role){
        if (userId < 1) {
            throw new IllegalArgumentException("User id can't be negative or zero");
        }
        Optional<UserEntity> foundUser = userRepository.findById(userId);
        foundUser.orElseThrow(()-> new IllegalArgumentException("User with the given id doesn't exist"));
        userRepository.updateUserRoleById(userId, role);
    }

    public void saveUser(RegUserDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.getRoleByName("USER");
        user.setRole(role);
        userRepository.save(user);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
