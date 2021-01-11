package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.vtcc.crawler.fb_bot.manage.entity.JobBotEntity;

public interface JobBotRepository extends JpaRepository<JobBotEntity, Integer> {
    JobBotEntity getFirstByIsProcessed(Byte o);

}
