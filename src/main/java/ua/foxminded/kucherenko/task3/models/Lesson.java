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
    private Group group;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    private Course course;

    public DayOfWeek getDayOfWeek(){
        return startTime.getDayOfWeek();
    }
}
