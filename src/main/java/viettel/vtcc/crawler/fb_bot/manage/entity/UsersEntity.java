package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "botfb_db_v3", catalog = "")
public class UsersEntity {
    private int id;
    private String name;
    private String userLogin;
    private String userPassword;
    private Timestamp createdAt;
    private Timestamp expiredDate;
    private Timestamp lastLogin;
    private Integer status;
    private Collection<BotHistoryEntity> botHistoriesById;
    private Collection<JobBotEntity> jobBotsById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "user_login")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
    @Column(name = "expired_date")
    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Basic
    @Column(name = "last_login")
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(userLogin, that.userLogin) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(expiredDate, that.expiredDate) &&
                Objects.equals(lastLogin, that.lastLogin) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userLogin, userPassword, createdAt, expiredDate, lastLogin, status);
    }

    @OneToMany(mappedBy = "usersByUserAction")
    public Collection<BotHistoryEntity> getBotHistoriesById() {
        return botHistoriesById;
    }

    public void setBotHistoriesById(Collection<BotHistoryEntity> botHistoriesById) {
        this.botHistoriesById = botHistoriesById;
    }

    @OneToMany(mappedBy = "usersByUserAction")
    public Collection<JobBotEntity> getJobBotsById() {
        return jobBotsById;
    }

    public void setJobBotsById(Collection<JobBotEntity> jobBotsById) {
        this.jobBotsById = jobBotsById;
    }
}
