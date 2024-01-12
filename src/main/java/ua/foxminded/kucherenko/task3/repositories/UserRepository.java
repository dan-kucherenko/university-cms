package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findFirstByUsername(String username);
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u  SET u.role = :role WHERE u.id = :id")
    void updateUserRoleById(Long id, Role role);
}
