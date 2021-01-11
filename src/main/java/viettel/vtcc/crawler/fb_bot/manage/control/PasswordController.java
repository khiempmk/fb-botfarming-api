package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import viettel.vtcc.crawler.fb_bot.manage.factory.CheckRequireField;
import viettel.vtcc.crawler.fb_bot.manage.factory.Processor;
import viettel.vtcc.crawler.fb_bot.manage.factory.ReturnHelper;
import viettel.vtcc.crawler.fb_bot.manage.model.FunctionParamObject;
import viettel.vtcc.crawler.fb_bot.manage.model.ImportBotRequestObject;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class PasswordController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;

    @PutMapping(value = "/api/bot_crawler/updated_password", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public  String updatePassword(@RequestBody FunctionParamObject request){
        try {
            log.info("New request path api/bot_crawler/updated_password");
            if (checkRequireField.chargePasswordCheckFields(request)) {
                    JsonArray data = processor.chargePassword(request.getUid(), request.getOld_password() , request.getNew_password());
                    return returnHelper.formResultSuccess(data);
            } else {
                return  returnHelper.formResultFailed("Not enough require field");
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  returnHelper.formResultFailed();
        }
    }

    @GetMapping("/api/bot_crawler/generate_password")
    public String genaratePassword(@RequestParam Map<String,String> queries) {
        try {
            log.info("New request path /api/bot_crawler/generate_password");
            if (queries.containsKey("uid") ){
                String uid  = queries.get("uid");
                if (uid != null) {
                    JsonArray data = processor.generatePassword(uid);
                    if (data != null){
                        return  returnHelper.formResultSuccess(data);
                    }
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
