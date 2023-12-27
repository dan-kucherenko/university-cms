package ua.foxminded.kucherenko.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.kucherenko.task3.models.Group;
import ua.foxminded.kucherenko.task3.services.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    public String groups(Model model,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "pageSize", defaultValue = "10") int size) {
        final Page<Group> groupsPage = groupService.getAllGroups(page, size);
        model.addAttribute("groups", groupsPage.getContent());
        return "show_pages/groups";
    }
}
