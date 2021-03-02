package musicdb.demo.services.implementations;

import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.UserRoleEntity;
import musicdb.demo.models.entities.enums.UserRoleEnum;
import musicdb.demo.repositories.UserRepository;
import musicdb.demo.repositories.UserRoleRepository;
import musicdb.demo.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {
        if(userRepository.count()==0){
            UserRoleEntity admin =  new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity user =  new UserRoleEntity().setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin, user));
            UserEntity adminUser = new UserEntity().setName("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setRoles(List.of(admin, user));
            UserEntity userUser = new UserEntity().setName("user")
                    .setPassword(passwordEncoder.encode("user"))
                    .setRoles(List.of(user));
            userRepository.saveAll(List.of(adminUser, userUser));
        }
    }
}
