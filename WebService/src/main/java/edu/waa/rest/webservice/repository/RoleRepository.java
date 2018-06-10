package edu.waa.rest.webservice.repository;

import edu.waa.rest.webservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository("roleRepository")
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);

}
