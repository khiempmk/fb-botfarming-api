package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotOtpEntity;

import java.sql.Timestamp;
import java.util.List;

public interface BotOtpRepository  extends JpaRepository<BotOtpEntity, String> {
//    List<BotOtpEntity> getBotOtpEntitiesByUidAndCreatedAtBetween(String uid , Timestamp t1 , Timestamp t2);
    List<BotOtpEntity> getAllByUidAndCreatedAtAfter(String uid, Timestamp createAt);
}
