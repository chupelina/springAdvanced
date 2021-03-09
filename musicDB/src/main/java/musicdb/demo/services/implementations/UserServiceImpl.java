package musicdb.demo.services.implementations;

import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.UserRoleEntity;
import musicdb.demo.models.entities.enums.UserRoleEnum;
import musicdb.demo.models.serviceModels.UserRegistrationServiceModel;
import musicdb.demo.repositories.UserRepository;
import musicdb.demo.repositories.UserRoleRepository;
import musicdb.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MusicDBUserService musicDBUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MusicDBUserService musicDBUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.musicDBUserService = musicDBUserService;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity().setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(admin, user));
            UserEntity adminUser = new UserEntity().setUsername("admin").setFullName("Stamat")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setRoles(List.of(admin, user));
            UserEntity userUser = new UserEntity().setUsername("user").setFullName("Lishko")
                    .setPassword(passwordEncoder.encode("user"))
                    .setRoles(List.of(user));
            userRepository.saveAll(List.of(adminUser, userUser));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity userEntity = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .addRole(userRoleRepository.findByRole(UserRoleEnum.USER).get());
        userEntity = userRepository.save(userEntity);

        UserDetails principal = musicDBUserService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean isExistingWithSameName(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
