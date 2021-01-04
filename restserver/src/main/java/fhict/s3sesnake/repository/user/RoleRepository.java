package fhict.s3sesnake.repository.user;

import fhict.s3sesnake.models.user.Role;
import fhict.s3sesnake.models.user.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
