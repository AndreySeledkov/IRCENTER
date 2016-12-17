package org.ircenter.server.dao;

import org.ircenter.serv.ImageServer;
import org.ircenter.server.dao.calendarEvent.CalendarEventDAO;
import org.ircenter.server.dao.comment.CommentDAO;
import org.ircenter.server.dao.edit.UserContactsDAO;
import org.ircenter.server.dao.edit.UserInterestsDAO;
import org.ircenter.server.dao.evidence.*;
import org.ircenter.server.dao.favorite.FavoritePostDAO;
import org.ircenter.server.dao.forum.*;
import org.ircenter.server.dao.friends.FriendsDAO;
import org.ircenter.server.dao.friends.FriendsRequestDAO;
import org.ircenter.server.dao.group.CommunityGroupDAO;
import org.ircenter.server.dao.group.GroupDAO;
import org.ircenter.server.dao.group.MeetingGroupDAO;
import org.ircenter.server.dao.group.UserGroupDAO;
import org.ircenter.server.dao.images.ImageDAO;
import org.ircenter.server.dao.like.LikeDAO;
import org.ircenter.server.dao.like.LikeQuantityDAO;
import org.ircenter.server.dao.location.UserLocationDAO;
import org.ircenter.server.dao.molodejhka.MolodejhkaDAO;
import org.ircenter.server.dao.news.NewsCommentDAO;
import org.ircenter.server.dao.news.NewsDAO;
import org.ircenter.server.dao.news.NewsSectionDAO;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.ircenter.server.dao.privatemessage.PrivateMessageDAO;
import org.ircenter.server.dao.registration.RegistrationDAO;
import org.ircenter.server.dao.slider.SliderDAO;
import org.ircenter.server.dao.statistic.UserStatisticDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.calendarEvent.CalendarEventService;
import org.ircenter.server.service.like.LikeService;
import org.ircenter.server.service.molodejhka.MolodejhkaService;
import org.ircenter.server.service.news.NewsService;
import org.ircenter.server.service.user.options.UserOptionService;
import org.ircenter.server.service.user.options.UserOptionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.02.12
 * Time: 9:43
 */

@Repository
public class Database {
    private RegistrationDAO registrationDAO;
    private SettingDAO settingDAO;
    private UserLocationDAO userLocationDAO;
    private UserStatisticDAO userStatisticDAO;
    private PrivateMessageDAO privateMessageDAO;
    private UserContactsDAO userContactsDAO;
    private UserInterestsDAO userInterestsDAO;
    private MessageDAO messageDAO;
    private PartsDAO partsDAO;
    private ThemeDAO themeDAO;
    private UserThemeLogDAO userThemeLogDAO;
    private FriendsDAO friendsDAO;
    private FriendsRequestDAO friendsRequestDAO;
    private UserInfoDAO userInfoDAO;
    private GroupDAO groupDAO;
    private MeetingGroupDAO mettingGroupDAO;
    private CommunityGroupDAO communityGroupDAO;
    private NewsSectionDAO newsSectionDAO;
    private NewsDAO newsDAO;
    private NewsCommentDAO newsCommentDAO;
    private LikeDAO likeDAO;
    private LikeQuantityDAO likeQuantityDAO;
    private LikeService likeService;
    private UserGroupDAO userGroupDAO;
    private SliderDAO sliderDAO;
    private FavoritePostDAO favoritePostDAO;
    private CommentDAO commentDAO;
    private UserOptionsDAO userOptionsDAO;
    private FeedBackDAO feedBackDAO;
    private TextEvidenceDAO textEvidenceDAO;
    private VideoEvidenceDAO videoEvidenceDAO;
    private MinutesToGodDAO minutesToGodDAO;
    private SecretSpiritualWorldDAO secretSpiritualWorldDAO;
    private OnlineEvidenceDAO onlineEvidenceDAO;
    private TvDAO tvDAO;
    private OnlineTvDAO onlineTvDAO;

    private CalendarEventDAO calendarEventDAO;
    private ImageDAO imageDAO;
    private DeliveriesDAO deliveriesDAO;
    private EvidenceHealingDAO evidenceHealingDAO;
    private MolodejhkaDAO molodejhkaDAO;
    private MolodejhkaService molodejhkaService;

    public MolodejhkaDAO getMolodejhkaDAO() {
        return molodejhkaDAO;
    }

    @Autowired
    public void setMolodejhkaDAO(MolodejhkaDAO molodejhkaDAO) {
        this.molodejhkaDAO = molodejhkaDAO;
    }

    public MolodejhkaService getMolodejhkaService() {
        return molodejhkaService;
    }

    @Autowired
    public void setMolodejhkaService(MolodejhkaService molodejhkaService) {
        this.molodejhkaService = molodejhkaService;
    }

    public org.ircenter.serv.dataserver.images.impl.ImageDAO getImgDAO() {
        return imgDAO;
    }

    @Autowired
    public void setImgDAO(org.ircenter.serv.dataserver.images.impl.ImageDAO imgDAO) {
        this.imgDAO = imgDAO;
    }

    private org.ircenter.serv.dataserver.images.impl.ImageDAO imgDAO;

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    private NewsService newsService;
    private CalendarEventService calendarEventService;
    private ProgrammTvDAO programmTvDAO;
    private PartnerDAO partnerDAO;
    private PartnerPrayerDAO partnerPrayerDAO;

    private PastorTreatmentDAO pastorTreatmentDAO;
    private BusDAO busDAO;

    private ImageServer imageServer;

    public PartnerPrayerDAO getPartnerPrayerDAO() {
        return partnerPrayerDAO;
    }

    @Autowired
    public void setPartnerPrayerDAO(PartnerPrayerDAO partnerPrayerDAO) {
        this.partnerPrayerDAO = partnerPrayerDAO;
    }

    public ImageServer getImageServer() {
        return imageServer;
    }

    @Autowired
    public void setImageServer(ImageServer imageServer) {
        this.imageServer = imageServer;
    }

    public CalendarEventService getCalendarEventService() {
        return calendarEventService;
    }

    @Autowired
    public void setCalendarEventService(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    private UserOptionService userOptionService;

    public UserOptionsDAO getUserOptionsDAO() {
        return userOptionsDAO;
    }

    public void setUserOptionsDAO(UserOptionsDAO userOptionsDAO) {
        this.userOptionsDAO = userOptionsDAO;
    }

    public UserOptionService getUserOptionService() {
        return userOptionService;
    }

    public void setUserOptionService(UserOptionService userOptionService) {
        this.userOptionService = userOptionService;
    }

    public FavoritePostDAO getFavoritePostDAO() {
        return favoritePostDAO;
    }

    public FeedBackDAO getFeedBackDAO() {
        return feedBackDAO;
    }

    @Autowired
    public void setFeedBackDAO(FeedBackDAO feedBackDAO) {
        this.feedBackDAO = feedBackDAO;
    }

    @Autowired
    public void setFavoritePostDAO(FavoritePostDAO favoritePostDAO) {
        this.favoritePostDAO = favoritePostDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public UserGroupDAO getUserGroupDAO() {
        return userGroupDAO;
    }

    @Autowired
    public void setUserGroupDAO(UserGroupDAO userGroupDAO) {
        this.userGroupDAO = userGroupDAO;
    }

    public NewsSectionDAO getNewsSectionDAO() {
        return newsSectionDAO;
    }

    @Autowired
    public void setNewsSectionDAO(NewsSectionDAO newsSectionDAO) {
        this.newsSectionDAO = newsSectionDAO;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

    @Autowired
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    public GroupDAO getGroupDAO() {
        return groupDAO;
    }

    @Autowired
    public void setGroupDAO(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public RegistrationDAO getRegistrationDAO() {
        return registrationDAO;
    }

    public SettingDAO getSettingDAO() {
        return settingDAO;
    }

    public UserLocationDAO getUserLocationDAO() {
        return userLocationDAO;
    }

    public UserStatisticDAO getUserStatisticDAO() {
        return userStatisticDAO;
    }

    public PrivateMessageDAO getPrivateMessageDAO() {
        return privateMessageDAO;
    }

    public UserContactsDAO getUserContactsDAO() {
        return userContactsDAO;
    }

    public UserInterestsDAO getUserInterestsDAO() {
        return userInterestsDAO;
    }

    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    public PartsDAO getPartsDAO() {
        return partsDAO;
    }

    public ThemeDAO getThemeDAO() {
        return themeDAO;
    }

    public UserThemeLogDAO getUserThemeLogDAO() {
        return userThemeLogDAO;
    }

    public FriendsDAO getFriendsDAO() {
        return friendsDAO;
    }

    public FriendsRequestDAO getFriendsRequestDAO() {
        return friendsRequestDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public MeetingGroupDAO getMettingGroupDAO() {
        return mettingGroupDAO;
    }

    public SliderDAO getSliderDAO() {
        return sliderDAO;
    }

    @Autowired
    public void setSliderDAO(SliderDAO sliderDAO) {
        this.sliderDAO = sliderDAO;
    }

    @Autowired
    public void setMettingGroupDAO(MeetingGroupDAO mettingGroupDAO) {
        this.mettingGroupDAO = mettingGroupDAO;
    }

    public CommunityGroupDAO getCommunityGroupDAO() {
        return communityGroupDAO;
    }

    @Autowired
    public void setCommunityGroupDAO(CommunityGroupDAO communityGroupDAO) {
        this.communityGroupDAO = communityGroupDAO;
    }

    @Autowired
    public void setRegistrationDAO(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @Autowired
    public void setSettingDAO(SettingDAO settingDAO) {
        this.settingDAO = settingDAO;
    }

    @Autowired
    public void setUserLocationDAO(UserLocationDAO userLocationDAO) {
        this.userLocationDAO = userLocationDAO;
    }

    @Autowired
    public void setUserStatisticDAO(UserStatisticDAO userStatisticDAO) {
        this.userStatisticDAO = userStatisticDAO;
    }

    @Autowired
    public void setPrivateMessageDAO(PrivateMessageDAO privateMessageDAO) {
        this.privateMessageDAO = privateMessageDAO;
    }

    @Autowired
    public void setUserContactsDAO(UserContactsDAO userContactsDAO) {
        this.userContactsDAO = userContactsDAO;
    }

    @Autowired
    public void setUserInterestsDAO(UserInterestsDAO userInterestsDAO) {
        this.userInterestsDAO = userInterestsDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Autowired
    public void setPartsDAO(PartsDAO partsDAO) {
        this.partsDAO = partsDAO;
    }

    @Autowired
    public void setThemeDAO(ThemeDAO themeDAO) {
        this.themeDAO = themeDAO;
    }

    @Autowired
    public void setFriendsDAO(FriendsDAO friendsDAO) {
        this.friendsDAO = friendsDAO;
    }

    @Autowired
    public void setFriendsRequestDAO(FriendsRequestDAO friendsRequestDAO) {
        this.friendsRequestDAO = friendsRequestDAO;
    }

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public CalendarEventDAO getCalendarEventDAO() {
        return calendarEventDAO;
    }

    @Autowired
    public void setCalendarEventDAO(CalendarEventDAO calendarEventDAO) {
        this.calendarEventDAO = calendarEventDAO;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public NewsCommentDAO getNewsCommentDAO() {
        return newsCommentDAO;
    }

    @Autowired
    public void setNewsCommentDAO(NewsCommentDAO newsCommentDAO) {
        this.newsCommentDAO = newsCommentDAO;
    }

    public TextEvidenceDAO getTextEvidenceDAO() {
        return textEvidenceDAO;
    }

    @Autowired
    public void setTextEvidenceDAO(TextEvidenceDAO textEvidenceDAO) {
        this.textEvidenceDAO = textEvidenceDAO;
    }

    public VideoEvidenceDAO getVideoEvidenceDAO() {
        return videoEvidenceDAO;
    }

    @Autowired
    public void setVideoEvidenceDAO(VideoEvidenceDAO videoEvidenceDAO) {
        this.videoEvidenceDAO = videoEvidenceDAO;
    }

    public OnlineEvidenceDAO getOnlineEvidenceDAO() {
        return onlineEvidenceDAO;
    }

    @Autowired
    public void setOnlineEvidenceDAO(OnlineEvidenceDAO onlineEvidenceDAO) {
        this.onlineEvidenceDAO = onlineEvidenceDAO;
    }

    public TvDAO getTvDAO() {
        return tvDAO;
    }

    @Autowired
    public void setTvDAO(TvDAO tvDAO) {
        this.tvDAO = tvDAO;
    }

    public MinutesToGodDAO getMinutesToGodDAO() {
        return minutesToGodDAO;
    }

    @Autowired
    public void setMinutesToGodDAO(MinutesToGodDAO minutesToGodDAO) {
        this.minutesToGodDAO = minutesToGodDAO;
    }

    public SecretSpiritualWorldDAO getSecretSpiritualWorldDAO() {
        return secretSpiritualWorldDAO;
    }

    @Autowired
    public void setSecretSpiritualWorldDAO(SecretSpiritualWorldDAO secretSpiritualWorldDAO) {
        this.secretSpiritualWorldDAO = secretSpiritualWorldDAO;
    }

    public ProgrammTvDAO getProgrammTvDAO() {
        return programmTvDAO;
    }

    @Autowired
    public void setProgrammTvDAO(ProgrammTvDAO programmTvDAO) {
        this.programmTvDAO = programmTvDAO;
    }

    public PartnerDAO getPartnerDAO() {
        return partnerDAO;
    }

    @Autowired
    public void setPartnerDAO(PartnerDAO partnerDAO) {
        this.partnerDAO = partnerDAO;
    }

    public PastorTreatmentDAO getPastorTreatmentDAO() {
        return pastorTreatmentDAO;
    }

    @Autowired
    public void setPastorTreatmentDAO(PastorTreatmentDAO pastorTreatmentDAO) {
        this.pastorTreatmentDAO = pastorTreatmentDAO;
    }

    public DeliveriesDAO getDeliveriesDAO() {
        return deliveriesDAO;
    }

    @Autowired
    public void setDeliveriesDAO(DeliveriesDAO deliveriesDAO) {
        this.deliveriesDAO = deliveriesDAO;
    }

    public EvidenceHealingDAO getEvidenceHealingDAO() {
        return evidenceHealingDAO;
    }

    @Autowired
    public void setEvidenceHealingDAO(EvidenceHealingDAO evidenceHealingDAO) {
        this.evidenceHealingDAO = evidenceHealingDAO;
    }

    public BusDAO getBusDAO() {
        return busDAO;
    }

    @Autowired
    public void setBusDAO(BusDAO busDAO) {
        this.busDAO = busDAO;
    }

    public LikeDAO getLikeDAO() {
        return likeDAO;
    }

    @Autowired
    public void setLikeDAO(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public LikeQuantityDAO getLikeQuantityDAO() {
        return likeQuantityDAO;
    }

    @Autowired
    public void setLikeQuantityDAO(LikeQuantityDAO likeQuantityDAO) {
        this.likeQuantityDAO = likeQuantityDAO;
    }

    public LikeService getLikeService() {
        return likeService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public OnlineTvDAO getOnlineTvDAO() {
        return onlineTvDAO;
    }

    @Autowired
    public void setOnlineTvDAO(OnlineTvDAO onlineTvDAO) {
        this.onlineTvDAO = onlineTvDAO;
    }
}
