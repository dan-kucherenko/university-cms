package ua.foxminded.kucherenko.task3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int lessonId;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;
    private String location;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public DayOfWeek getDayOfWeek(){
        return startTime.getDayOfWeek();
    }
}
