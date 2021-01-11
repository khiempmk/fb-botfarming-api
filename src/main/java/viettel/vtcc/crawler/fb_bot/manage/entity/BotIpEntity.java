package viettel.vtcc.crawler.fb_bot.manage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bot_ip", schema = "botfb_db_v3", catalog = "")
public class BotIpEntity {
    private String uid;
    private String ip;
    private String subnet;
    private String gateway;
    private String proxy;
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
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "subnet")
    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    @Basic
    @Column(name = "gateway")
    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Basic
    @Column(name = "proxy")
    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotIpEntity that = (BotIpEntity) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(subnet, that.subnet) &&
                Objects.equals(gateway, that.gateway) &&
                Objects.equals(proxy, that.proxy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, ip, subnet, gateway, proxy);
    }

    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false, table = "bot_ip")
    public BotProfileEntity getBotProfileByUid() {
        return botProfileByUid;
    }

    public void setBotProfileByUid(BotProfileEntity botProfileByUid) {
        this.botProfileByUid = botProfileByUid;
    }
}
