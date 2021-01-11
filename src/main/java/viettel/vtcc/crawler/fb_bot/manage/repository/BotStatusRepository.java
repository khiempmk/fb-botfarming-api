package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotProfileEntity;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotStatusEntity;

import java.util.List;

@Repository
public interface BotStatusRepository extends JpaRepository<BotStatusEntity, String> {
    List<BotStatusEntity> getAllByStatus(int status);
}
