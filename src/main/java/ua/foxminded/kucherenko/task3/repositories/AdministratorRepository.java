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
    @Query("DELETE FROM Administrator a WHERE a.user.id = :userId")
    void deleteAdministratorByUserId(long userId);
}
