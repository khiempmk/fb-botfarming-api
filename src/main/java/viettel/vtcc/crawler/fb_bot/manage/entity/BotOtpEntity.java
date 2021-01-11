package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bot_otp", schema = "botfb_db_v3", catalog = "")
public class BotOtpEntity {
    private int id;
    private String uid;
    private Integer otpSms;
    private Timestamp createdAt;
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
    @Column(name = "otp_sms")
    public Integer getOtpSms() {
        return otpSms;
    }

    public void setOtpSms(Integer otpSms) {
        this.otpSms = otpSms;
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
        BotOtpEntity that = (BotOtpEntity) o;
        return id == that.id &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(otpSms, that.otpSms) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, otpSms, createdAt);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", table = "bot_otp")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }
}
