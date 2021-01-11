package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bot_history", schema = "botfb_db_v3", catalog = "")
public class BotHistoryEntity {
    private int id;
    private String uid;
    private Integer userAction;
    private Integer oldStatus;
    private Integer newStatus;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private BotProfileEntity botProfileByUid;
    private UsersEntity usersByUserAction;

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
    @Column(name = "uid", insertable = false , updatable = false)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "user_action", insertable = false , updatable = false)
    public Integer getUserAction() {
        return userAction;
    }

    public void setUserAction(Integer userAction) {
        this.userAction = userAction;
    }

    @Basic
    @Column(name = "old_status")
    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    @Basic
    @Column(name = "new_status")
    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
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
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotHistoryEntity that = (BotHistoryEntity) o;
        return id == that.id &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(userAction, that.userAction) &&
                Objects.equals(oldStatus, that.oldStatus) &&
                Objects.equals(newStatus, that.newStatus) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, userAction, oldStatus, newStatus, updatedAt, createdAt);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", table = "bot_history")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }

    @ManyToOne
    @JoinColumn(name = "user_action", referencedColumnName = "id", table = "bot_history")
    public UsersEntity getUsersByUserAction() {
        return usersByUserAction;
    }

    public void setUsersByUserAction(UsersEntity usersByUserAction) {
        this.usersByUserAction = usersByUserAction;
    }
}
