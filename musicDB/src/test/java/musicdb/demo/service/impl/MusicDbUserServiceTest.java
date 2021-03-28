package musicdb.demo.service.impl;

import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.UserRoleEntity;
import musicdb.demo.models.entities.enums.UserRoleEnum;
import musicdb.demo.repositories.UserRepository;
import musicdb.demo.services.implementations.MusicDBUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class MusicDbUserServiceTest {
    private MusicDBUserService serviceToTest;

    @Mock
    UserRepository mockedUserRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new MusicDBUserService(mockedUserRepository);
    }

    @Test
    public void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,()->
                serviceToTest.loadUserByUsername("user_does_not_exists"));
    }

    @Test
    public void testUserFound(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("aaa").setPassword("111")
                .setRoles(List.of(new UserRoleEntity().setRole(UserRoleEnum.USER),new UserRoleEntity().setRole(UserRoleEnum.ADMIN)))
                .setFullName("aaabbb");

        Mockito.when(mockedUserRepository.findByUsername("aaa")).thenReturn(Optional.of(userEntity));

        UserDetails testingUSer = serviceToTest.loadUserByUsername("aaa");

        Assertions.assertEquals(userEntity.getUsername(), testingUSer.getUsername());
        Assertions.assertEquals(userEntity.getPassword(), testingUSer.getPassword());
        Assertions.assertEquals(2, testingUSer.getAuthorities().size());
        List<String> authorities = testingUSer.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }

}
