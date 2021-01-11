package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotCrawlerEntity;

@Repository
public interface BotCrawlerRepository extends JpaRepository<BotCrawlerEntity, String> {
}