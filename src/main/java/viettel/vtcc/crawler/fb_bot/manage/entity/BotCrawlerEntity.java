package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bot_crawler", schema = "botfb_db_v3", catalog = "")
public class BotCrawlerEntity {
    private int id;
    private String uid;
    private String cookie;
    private String fbDtsg;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BotProfileEntity botProfileByUid;

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
    @Column(name = "cookie")
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Basic
    @Column(name = "fb_dtsg")
    public String getFbDtsg() {
        return fbDtsg;
    }

    public void setFbDtsg(String fbDtsg) {
        this.fbDtsg = fbDtsg;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotCrawlerEntity that = (BotCrawlerEntity) o;
        return id == that.id &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(cookie, that.cookie) &&
                Objects.equals(fbDtsg, that.fbDtsg) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, cookie, fbDtsg, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", table = "bot_crawler")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }
}
