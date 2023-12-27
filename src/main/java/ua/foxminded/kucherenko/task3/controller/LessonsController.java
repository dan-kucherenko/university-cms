package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonsController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public String lessons(Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "show_pages/lessons";
    }
}
