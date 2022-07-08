package bg.hoteltrip.model.entity;

import bg.hoteltrip.model.entity.enums.UserRoleName;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleName userRole;




}
