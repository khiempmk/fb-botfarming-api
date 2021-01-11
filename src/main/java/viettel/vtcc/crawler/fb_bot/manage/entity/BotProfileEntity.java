package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bot_profile", schema = "botfb_db_v3", catalog = "")
public class BotProfileEntity {
    private String uid;
    private String name;
    private String phone;
    private String password;
    private String country;
    private String education;
    private String gender;
    private String birthday;
    private Integer numberFriend;
    private Integer numberFollow;
    private String sourceAvatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastTimeDead;
    private Collection<BotCrawlerEntity> botCrawlersByUid;
    private Collection<BotHistoryEntity> botHistoriesByUid;
    private BotIpEntity botIpByUid;
    private Collection<BotOtpEntity> botOtpsByUid;
    private BotStatusEntity botStatusByUid;
    private Collection<JobBotEntity> jobBotsByUid;

    @Id
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "number_friend")
    public Integer getNumberFriend() {
        return numberFriend;
    }

    public void setNumberFriend(Integer numberFriend) {
        this.numberFriend = numberFriend;
    }

    @Basic
    @Column(name = "number_follow")
    public Integer getNumberFollow() {
        return numberFollow;
    }

    public void setNumberFollow(Integer numberFollow) {
        this.numberFollow = numberFollow;
    }

    @Basic
    @Column(name = "source_avatar")
    public String getSourceAvatar() {
        return sourceAvatar;
    }

    public void setSourceAvatar(String sourceAvatar) {
        this.sourceAvatar = sourceAvatar;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "last_time_dead")
    public Timestamp getLastTimeDead() {
        return lastTimeDead;
    }

    public void setLastTimeDead(Timestamp lastTimeDead) {
        this.lastTimeDead = lastTimeDead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotProfileEntity that = (BotProfileEntity) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(password, that.password) &&
                Objects.equals(country, that.country) &&
                Objects.equals(education, that.education) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(numberFriend, that.numberFriend) &&
                Objects.equals(numberFollow, that.numberFollow) &&
                Objects.equals(sourceAvatar, that.sourceAvatar) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(lastTimeDead, that.lastTimeDead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, phone, password, country, education, gender, birthday, numberFriend, numberFollow, sourceAvatar, createdAt, updatedAt, lastTimeDead);
    }

    @OneToMany(mappedBy = "botProfileByUid")
    public Collection<BotCrawlerEntity> getBotCrawlersByUid() {
        return botCrawlersByUid;
    }

    public void setBotCrawlersByUid(Collection<BotCrawlerEntity> botCrawlersByUid) {
        this.botCrawlersByUid = botCrawlersByUid;
    }

    @OneToMany(mappedBy = "botProfileByUid")
    public Collection<BotHistoryEntity> getBotHistoriesByUid() {
        return botHistoriesByUid;
    }

    public void setBotHistoriesByUid(Collection<BotHistoryEntity> botHistoriesByUid) {
        this.botHistoriesByUid = botHistoriesByUid;
    }

    @OneToOne(mappedBy = "botProfileByUid")
    public BotIpEntity getBotIpByUid() {
        return botIpByUid;
    }

    public void setBotIpByUid(BotIpEntity botIpByUid) {
        this.botIpByUid = botIpByUid;
    }

    @OneToMany(mappedBy = "botProfileByUid")
    public Collection<BotOtpEntity> getBotOtpsByUid() {
        return botOtpsByUid;
    }

    public void setBotOtpsByUid(Collection<BotOtpEntity> botOtpsByUid) {
        this.botOtpsByUid = botOtpsByUid;
    }

    @OneToOne(mappedBy = "botProfileByUid")
    public BotStatusEntity getBotStatusByUid() {
        return botStatusByUid;
    }

    public void setBotStatusByUid(BotStatusEntity botStatusByUid) {
        this.botStatusByUid = botStatusByUid;
    }

    @OneToMany(mappedBy = "botProfileByUid")
    public Collection<JobBotEntity> getJobBotsByUid() {
        return jobBotsByUid;
    }

    public void setJobBotsByUid(Collection<JobBotEntity> jobBotsByUid) {
        this.jobBotsByUid = jobBotsByUid;
    }
}
