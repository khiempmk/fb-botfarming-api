package viettel.vtcc.crawler.fb_bot.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.vtcc.crawler.fb_bot.manage.entity.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findFirstByUserLoginAndUserPassword(String userName, String password);
}
