package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Group;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;
import ua.foxminded.kucherenko.task3.repositories.GroupRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        LOGGER.debug("Getting all the groups");
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(int id) {
        LOGGER.debug("Looking for a group by id");
        return groupRepository.findById(id);
    }

    public List<GroupStudentsInfo> getGroupsByStudentNum(int studentsNum) {
        LOGGER.debug("Getting groups by student num");
        return groupRepository.getGroupByStudentNum(studentsNum);
    }

    public List<Integer> getAllGroupIds() {
        LOGGER.debug("Getting all group ids");
        return groupRepository.getAllGroupIds();
    }

    public int getGroupQuantity(int groupId) {
        LOGGER.debug("Getting number of students in the group");
        return groupRepository.getGroupQuantity(groupId);
    }

    public void saveGroup(Group group) {
        LOGGER.debug("New group is saved");
        groupRepository.save(group);
    }

    public void updateGroup(int groupId, Group updatedGroup) {
        Optional<Group> existingGroupOptional = groupRepository.findById(groupId);

        if (existingGroupOptional.isPresent()) {
            Group existingGroup = existingGroupOptional.get();
            existingGroup.setName(updatedGroup.getName());
            existingGroup.setFaculty(updatedGroup.getFaculty());
            existingGroup.setSpeciality(updatedGroup.getSpeciality());
            existingGroup.setStudentsQuantity(updatedGroup.getStudentsQuantity());

            groupRepository.save(existingGroup);

            LOGGER.debug("Group with ID {} has been updated", groupId);
        } else {
            LOGGER.error("Group not found with ID: {}", groupId);
            throw new NoSuchElementException("Group not found with ID: " + groupId);
        }
    }

    public void deleteGroup(int id) {
        LOGGER.debug("Group deleted by id");
        groupRepository.deleteById(id);
    }
}
