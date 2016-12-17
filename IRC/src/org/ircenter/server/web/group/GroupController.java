package org.ircenter.server.web.group;

import org.ircenter.server.bean.group.*;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.group.GroupView;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String groups(Model model, HttpServletRequest request) {

        List<UsersGroup> usersGroups = database.getUserGroupDAO().getUsersGroup();
        List<GroupView> groupViews = new ArrayList<GroupView>(usersGroups.size());

        for (UsersGroup usersGroup : usersGroups) {
            GroupView groupView = new GroupView();

            groupView.setId(usersGroup.getId());
            groupView.setGroupId(usersGroup.getGroupId());
            groupView.setUserId(UserHelper.getUserId());
            groupView.setRoleType(usersGroup.getRoleType());

            Group group = database.getGroupDAO().getGroupById(usersGroup.getGroupId());

            groupView.setTitle(group.getTitle());
            groupView.setDescription(group.getDescription());
            groupView.setCountryId(group.getCountryId());
            groupView.setRegionId(group.getRegionId());
            groupView.setCityId(group.getCityId());
            groupView.setAssociationType(group.getAssociationType());

            switch (group.getAssociationType()) {
                case COMMUNITY:
                    CommunityGroup communityGroup = database.getCommunityGroupDAO().getCommunityGroupById(group.getId());
                    groupView.setThemeId(communityGroup.getThemeId());
                    groupView.setSubsectionId(communityGroup.getSubsectionId());
                    break;
                case MEETING:
                    MeetingGroup meetingGroup = database.getMettingGroupDAO().getMeetingGroupById(group.getId());

                    groupView.setStartTime(meetingGroup.getStartTime());
                    groupView.setEndTime(meetingGroup.getEndTime());

                    break;
            }

            groupViews.add(groupView);
        }


        model.addAttribute("group", new Group());
        return "new/groups";
    }

    @RequestMapping(method = RequestMethod.GET, params = {"group_id", "act"})
    public String actGroup(@RequestParam Long group_id, String act, Model model, HttpServletRequest request,
                           HttpServletResponse httpResponse) {


        model.addAttribute("group", new Group());
        return "new/groups?group_id=" + group_id + "&act=edit";
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("group") Group group, HttpServletRequest request, Model model) {

        Group savedUserGroup = database.getGroupDAO().saveUserGroup(group);

        return "groups?group_id=" + savedUserGroup.getId() + "&act=edit";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
