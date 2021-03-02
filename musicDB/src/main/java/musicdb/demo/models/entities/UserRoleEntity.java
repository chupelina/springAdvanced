package musicdb.demo.models.entities;

import musicdb.demo.models.entities.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
