package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import viettel.vtcc.crawler.fb_bot.manage.factory.CheckRequireField;
import viettel.vtcc.crawler.fb_bot.manage.factory.Processor;
import viettel.vtcc.crawler.fb_bot.manage.factory.ReturnHelper;
import viettel.vtcc.crawler.fb_bot.manage.model.FunctionParamObject;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class LoadPublicInfoBotController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;

    @PostMapping(value = "/api/bot_crawler/public_info", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String loadPublicInfoBot(@RequestBody FunctionParamObject request) {
        try {
            if (checkRequireField.loadPublicInfoBotCheckFields(request)) {
                log.info("New request path /api/bot_crawler/public_info");
                JsonArray data = processor.loadPublicInfoBot(request.getParam(), request.getListID());
                if (data != null) {
                    return returnHelper.formResultSuccess(data);
                }
            } else
                return  returnHelper.formResultFailed("Not enough require field");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnHelper.formResultFailed();
    }
}
