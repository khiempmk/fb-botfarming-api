package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viettel.vtcc.crawler.fb_bot.manage.entity.BotProfileEntity;

import java.util.List;

@Repository
public interface BotProfileRepository extends JpaRepository<BotProfileEntity, String> {
}
