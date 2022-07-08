package bg.hoteltrip.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToMany
    private List<UserRoleEntity> role;

}
