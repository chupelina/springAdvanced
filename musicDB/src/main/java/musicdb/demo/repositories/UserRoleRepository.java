package musicdb.demo.repositories;

import musicdb.demo.models.entities.UserRoleEntity;
import musicdb.demo.models.entities.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
Optional<UserRoleEntity> findByRole (UserRoleEnum userRoleEnum);
}
