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
            SELECT NEW GroupStudentsInfo (g.groupId, g.groupName, COUNT(s))
            FROM Student s
                     INNER JOIN Group g ON s.groupId = g.groupId
            GROUP BY g.groupId, g.groupName
            HAVING COUNT(g) <= :studentsNum""")
    List<GroupStudentsInfo> getGroupByStudentNum(int studentsNum);
    @Query("SELECT g.groupId FROM Group g")
    List<Integer> getAllGroupIds();
    @Query("""
            SELECT COUNT (*) AS num_of_students
            FROM Student s
            WHERE s.groupId = :groupId
            """)
    int getGroupQuantity(int groupId);
}
