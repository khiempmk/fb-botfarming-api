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
import viettel.vtcc.crawler.fb_bot.manage.factory.ReturnHelper;
import viettel.vtcc.crawler.fb_bot.manage.factory.Processor;
import viettel.vtcc.crawler.fb_bot.manage.model.ImportBotRequestObject;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class ImportBotController {
    @Autowired
    CheckRequireField checkRequireField;

    @Autowired
    ReturnHelper returnHelper;

    @Autowired
    Processor processor;

    @PostMapping(value = "/api/bot_crawler/import", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public  String importAccount(@RequestBody List<ImportBotRequestObject> requests){
        try {
            log.info("New request path /api/bot_crawler/import");
            if (requests != null ) {
                JsonArray resutlData = new JsonArray() ;
                for (ImportBotRequestObject request : requests) {
                    Boolean success = false ;
                    if (checkRequireField.importAccountCheckFields(request)) {
                        success = processor.importAccount(request);
                    }
                    if (success){
                        resutlData.add(returnHelper.formStatusSuccess(request.getFb_id(),"imported"));
                    } else
                        resutlData.add(returnHelper.formStatusFailed(request.getFb_id(),"not imported"));
                }
                return  returnHelper.formResultSuccess(resutlData);
            }
            else return  returnHelper.formResultFailed();
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  returnHelper.formResultFailed();
        }
    }
}
