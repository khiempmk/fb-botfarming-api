package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bot_status", schema = "botfb_db_v3", catalog = "")
public class BotStatusEntity {
    private String uid;
    private Integer status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp unlockedAt;
    private BotProfileEntity botProfileByUid;

    @Id
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "unlocked_at")
    public Timestamp getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(Timestamp unlockedAt) {
        this.unlockedAt = unlockedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotStatusEntity that = (BotStatusEntity) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(unlockedAt, that.unlockedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, status, createdAt, updatedAt, unlockedAt);
    }

    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false, table = "bot_status")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }
}
