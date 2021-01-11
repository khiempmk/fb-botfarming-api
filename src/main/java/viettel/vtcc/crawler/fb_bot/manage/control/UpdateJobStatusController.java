package viettel.vtcc.crawler.fb_bot.manage.control;

import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import viettel.vtcc.crawler.fb_bot.manage.factory.CheckRequireField;
import viettel.vtcc.crawler.fb_bot.manage.factory.Processor;
import viettel.vtcc.crawler.fb_bot.manage.factory.ReturnHelper;
import viettel.vtcc.crawler.fb_bot.manage.model.FunctionParamObject;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class UpdateJobStatusController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;
    @PutMapping(value = "/api/bot_crawler/update_job", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public  String updateJobStatus(@RequestBody FunctionParamObject request){
        try {
            log.info("New request path /api/bot_crawler/update_job");
            if (checkRequireField.updateJobStatusCheckFields(request)) {
                JsonArray data = processor.updateJobStatus(request.getJob_id(),request.getIs_completed(),request.getUser_action(),request.getUpdated_time());
                return returnHelper.formResultSuccess(data);
            } else {
                return  returnHelper.formResultFailed("Not enough require field");
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  returnHelper.formResultFailed();
        }
    }
}
