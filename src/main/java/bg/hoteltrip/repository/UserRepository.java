package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from UserEntity u join u.roles r " +
            "where r.role = bg.hoteltrip.model.entity.enums.RoleEnum.USER")
    List<UserEntity> findAllUsersByRoleUser();
}
