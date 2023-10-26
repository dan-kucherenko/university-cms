package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Group;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("""
            SELECT NEW GroupStudentsInfo (g.id, g.name, COUNT(s))
            FROM Student s
                     INNER JOIN Group g ON s.id = g.id
            GROUP BY g.id, g.name
            HAVING COUNT(g) <= :studentsNum""")
    List<GroupStudentsInfo> getGroupByStudentNum(int studentsNum);
    @Query("SELECT g.id FROM Group g")
    List<Integer> getAllGroupIds();
    @Query("""
            SELECT COUNT (*) AS num_of_students
            FROM Student s
            WHERE s.id = :groupId
            """)
    int getGroupQuantity(int groupId);
}
