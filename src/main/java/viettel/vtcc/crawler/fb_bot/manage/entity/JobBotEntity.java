package viettel.vtcc.crawler.fb_bot.manage.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "job_bot", schema = "botfb_db_v3", catalog = "")
public class JobBotEntity {
    private int jobId;
    private Integer jobType;
    private String uid;
    private Byte isCompleted;
    private Byte isProcessed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer userAction;
    private BotProfileEntity botProfileByUid;
    private UsersEntity usersByUserAction;

    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_type")
    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
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
    @Column(name = "is_completed")
    public Byte getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Byte isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Basic
    @Column(name = "is_processed")
    public Byte getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
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
    @Column(name = "user_action",insertable = false , updatable = false)
    public Integer getUserAction() {
        return userAction;
    }

    public void setUserAction(Integer userAction) {
        this.userAction = userAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobBotEntity that = (JobBotEntity) o;
        return jobId == that.jobId &&
                Objects.equals(jobType, that.jobType) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(isCompleted, that.isCompleted) &&
                Objects.equals(isProcessed, that.isProcessed) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(userAction, that.userAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, jobType, uid, isCompleted, isProcessed, createdAt, updatedAt, userAction);
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", table = "job_bot")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }

    @ManyToOne
    @JoinColumn(name = "user_action", referencedColumnName = "id", table = "job_bot")
    public UsersEntity getUsersByUserAction() {
        return usersByUserAction;
    }

    public void setUsersByUserAction(UsersEntity usersByUserAction) {
        this.usersByUserAction = usersByUserAction;
    }
}
