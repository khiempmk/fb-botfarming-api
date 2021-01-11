package viettel.vtcc.crawler.fb_bot.manage.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.nashorn.api.scripting.JSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReturnHelper {
    public String returnSuccess(){
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", "success");
            return jsonObject.toString();
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null ;

    }
    public String getNotEnoughRequireFieldResult(){
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", "failed");
            jsonObject.addProperty("detail", "not enough require fields");
            return jsonObject.toString();
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public String formResultSuccess(JsonArray data){
        JsonObject jsonObject = new JsonObject() ;
        jsonObject.addProperty("code","0");
        jsonObject.addProperty("message", "request successful");
        jsonObject.add("data", data);
        log.info(jsonObject.toString());
        return  jsonObject.toString() ;
    }

    public  String formResultFailed(){
        JsonObject jsonObject = new JsonObject() ;
        jsonObject.addProperty("code","1");
        jsonObject.addProperty("message", "request failed");
        log.info(jsonObject.toString());
        return  jsonObject.toString() ;
    }
    public  String formResultFailed(String message){
        JsonObject jsonObject = new JsonObject() ;
        jsonObject.addProperty("code","1");
        jsonObject.addProperty("message", message);
        log.info(jsonObject.toString());
        return  jsonObject.toString() ;
    }


    public  JsonObject formStatusSuccess(String id , String status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("status",  status); //"imported");
        return  jsonObject;
    }
    public  JsonObject formStatusSuccess(String property ,Integer data, String status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(property, data);
        jsonObject.addProperty("status",  status); //"imported");
        return  jsonObject;
    }
    public  JsonObject formStatusFailed(String id , String status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("status", status) ; //"Not imported");
        return  jsonObject;
    }
    public  JsonObject formStatusFailed(String property ,Integer data, String status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(property, data);
        jsonObject.addProperty("status", status) ; //"Not imported");
        return  jsonObject;
    }
}
