package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.UserEntity;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            INSERT INTO administrators (id, username, first_name, last_name, email, phone, role_id, password)
            VALUES (:id, :username, :firstName, :lastName, :email, :phone, :role, :password)
            """)
    void saveAdministratorFromUser(Long id, String username, String firstName, String lastName,
                                   String email, String phone, int role, String password);

    @Modifying
    @Transactional
    default void saveAdministratorFromUser(UserEntity user) {
        saveAdministratorFromUser(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPhone(), user.getRole().getId(), user.getPassword());
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM Administrator a WHERE a.id = :id")
    void deleteAdministratorByUserId(long id);
}
