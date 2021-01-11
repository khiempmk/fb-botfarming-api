package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
public class LoginController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;

    @PostMapping(value = "/api/bot_crawler/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginController(@RequestBody FunctionParamObject request) {
        try {
            log.info("New request path /api/bot_crawler/login");
            if (checkRequireField.loginCheckFields(request)) {
                JsonObject data = processor.login(request.getUser_login(), request.getUser_password());
                if (data != null) {
                    return data.toString();
                }
            } else {
                return  returnHelper.formResultFailed("Not enough require field") ;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnHelper.formResultFailed();
    }
}
