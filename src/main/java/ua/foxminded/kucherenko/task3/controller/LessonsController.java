package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Lesson;
import ua.foxminded.kucherenko.task3.services.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonsController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public String lessons(Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Lesson> lessonsPage = lessonService.getAllLessons(page, size);
        model.addAttribute("lessons", lessonsPage.getContent());
        model.addAttribute("pageNo", page + 1);
        model.addAttribute("totalPages", lessonsPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "show_pages/lessons";
    }
}
