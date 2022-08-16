package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(RoleEnum role);

}
