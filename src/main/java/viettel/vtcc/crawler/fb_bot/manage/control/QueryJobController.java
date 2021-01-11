package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
public class QueryJobController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;
    @GetMapping("/api/bot_crawler/query_job")
    public String queryJobController(@RequestParam Map<String,String> queries) {
        try {
            log.info("New request path /api/bot_crawler/query_job");
            if (queries.containsKey("user_action") ){
                String user_action  = queries.get("user_action");
                try {
                    Integer user = Integer.parseInt(user_action);
                    JsonArray data = processor.queryJob(user);
                    return returnHelper.formResultSuccess(data);
                } catch (Exception e) {
                    return returnHelper.formResultFailed("user_action must be Integer value");
                }
            } else {
                return  returnHelper.formResultFailed("Not enough require field") ;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return  returnHelper.formResultFailed();
    }
}
