package cybersoft.javabackend.java18.gira.role.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
   @Query("select o from Operation o left join o.roles r left join r.userGroups g left join g.users u where u.username = ?2 and o.name = ?1")
    List<Operation> findAllByNameAndUsername(String operationName, String username);
}
