package ua.foxminded.kucherenko.task3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.kucherenko.task3.models.Group;
import ua.foxminded.kucherenko.task3.models.GroupStudentsInfo;
import ua.foxminded.kucherenko.task3.repositories.GroupRepository;

import java.util.List;
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

    public Optional<Group> getGroupById(int groupId) {
        if (groupId < 1) {
            throw new IllegalArgumentException("Group id can't be negative or zero");
        }
        LOGGER.debug("Looking for a group by id");
        return groupRepository.findById(groupId);
    }

    public List<GroupStudentsInfo> getGroupsByStudentNum(int studentsNum) {
        if (studentsNum < 0) {
            throw new IllegalArgumentException("Students number can't be negative");
        }

        LOGGER.debug("Getting groups by student num");
        return groupRepository.getGroupByStudentsQuantity(studentsNum);
    }

    public List<Integer> getAllGroupIds() {
        LOGGER.debug("Getting all group ids");
        return groupRepository.getAllGroupIds();
    }

    public int getGroupQuantity(int groupId) {
        if (groupId < 1) {
            throw new IllegalArgumentException("Group id can't be negative or zero");
        }

        LOGGER.debug("Getting number of students in the group");
        return groupRepository.getGroupQuantity(groupId);
    }

    public void saveGroup(Group group) {
        LOGGER.debug("New group is saved");
        groupRepository.save(group);
    }

    public void updateGroup(int groupId, Group updatedGroup) {
        if (groupId < 1) {
            throw new IllegalArgumentException("Group id can't be negative or zero");
        }

        Optional<Group> existingGroupOptional = groupRepository.findById(groupId);
        existingGroupOptional.orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + groupId));

        Group existingGroup = existingGroupOptional.get();
        existingGroup.setGroupName(updatedGroup.getGroupName());
        existingGroup.setGroupFaculty(updatedGroup.getGroupFaculty());
        existingGroup.setGroupSpeciality(updatedGroup.getGroupSpeciality());

        groupRepository.save(existingGroup);

        LOGGER.debug("Group with ID {} has been updated", groupId);
    }


    public void deleteGroup(int groupId) {
        if (groupId < 1) {
            throw new IllegalArgumentException("Group id can't be negative or zero");
        }

        LOGGER.debug("Group deleted by id");
        groupRepository.deleteById(groupId);
    }
}
