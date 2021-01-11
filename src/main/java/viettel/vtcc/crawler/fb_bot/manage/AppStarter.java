package viettel.vtcc.crawler.fb_bot.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppStarter.class);
        app.run(args);
    }
}
