package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.kucherenko.task3.services.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    public String groups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "show_pages/groups";
    }
}
