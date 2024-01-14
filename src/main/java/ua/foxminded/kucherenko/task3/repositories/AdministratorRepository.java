package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            INSERT INTO administrators (id, username, first_name, last_name, email, phone, role_id, password)
            VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)
            """)
    void saveAdministratorFromUser(Long id, String username, String firstName, String lastName,
                                   String email, String phone, int role, String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM Administrator a WHERE a.id = :id")
    void deleteAdministratorByUserId(long id);
}
