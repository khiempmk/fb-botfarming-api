package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotHistoryEntity;

public interface BotHistoryRepository extends JpaRepository<BotHistoryEntity, Integer> {
}
