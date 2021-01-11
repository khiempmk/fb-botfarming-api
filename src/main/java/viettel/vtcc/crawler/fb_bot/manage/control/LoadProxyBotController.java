package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import viettel.vtcc.crawler.fb_bot.manage.factory.CheckRequireField;
import viettel.vtcc.crawler.fb_bot.manage.factory.Processor;
import viettel.vtcc.crawler.fb_bot.manage.factory.ReturnHelper;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class LoadProxyBotController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;

    @GetMapping("/api/bot_crawler/proxy_info")
    public String loadBotByStatus(@RequestParam Map<String,String> queries) {
        try {
            log.info("New request path /api/bot_crawler/proxy_info");
            if (queries.containsKey("uid") ){
                String uid  = queries.get("uid");
                if (uid != null) {
                    JsonArray data = processor.loadProxyInfoBot(uid);
                    if (data != null){
                        return  returnHelper.formResultSuccess(data);
                    }
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return  returnHelper.formResultFailed();
    }
}
