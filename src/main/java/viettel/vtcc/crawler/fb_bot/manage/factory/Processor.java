package viettel.vtcc.crawler.fb_bot.manage.factory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import viettel.vtcc.crawler.fb_bot.manage.entity.*;
import viettel.vtcc.crawler.fb_bot.manage.model.ImportBotRequestObject;
import viettel.vtcc.crawler.fb_bot.manage.repository.*;
import viettel.vtcc.crawler.fb_bot.manage.utils.NlpUtils;

import javax.management.timer.Timer;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class Processor {
    @Autowired
    BotCrawlerRepository botCrawlerRepository;
    @Autowired
    BotIPRepository botIPRepository ;
    @Autowired
    BotProfileRepository botProfileRepository ;
    @Autowired
    BotStatusRepository botStatusRepository ;
    @Autowired
    BotOtpRepository botOtpRepository ;
    @Autowired
    BotHistoryRepository botHistoryRepository ;

    @Autowired
    JobBotRepository jobBotRepository ;

    @Autowired
    UserRepository userRepository ;
    @Autowired
    ReturnHelper returnHelper ;
    public boolean importAccount(ImportBotRequestObject importBotRequestObject){
        try {
            // new bot profile
            BotProfileEntity botProfileEntity = new BotProfileEntity();
            botProfileEntity.setUid(importBotRequestObject.getFb_id());
            botProfileEntity.setName(importBotRequestObject.getFull_name());
            botProfileEntity.setPhone(importBotRequestObject.getPhone());
            botProfileEntity.setPassword(importBotRequestObject.getPassword());
            botProfileEntity.setCountry(importBotRequestObject.getCountry());
            botProfileEntity.setEducation(importBotRequestObject.getEducation());
            botProfileEntity.setGender(String.valueOf(importBotRequestObject.getGender()));
            botProfileEntity.setBirthday(importBotRequestObject.getBirthday());
            botProfileEntity.setNumberFriend(importBotRequestObject.getTotal_friend());
            botProfileEntity.setNumberFollow(importBotRequestObject.getNumber_follow());
            botProfileEntity.setSourceAvatar(importBotRequestObject.getProfile_picture());
            botProfileEntity.setCreatedAt(importBotRequestObject.getCreated_at());
            botProfileEntity.setUpdatedAt(importBotRequestObject.getUpdated_at());
            botProfileEntity.setLastTimeDead(importBotRequestObject.getCheckpoint_date());
            // new bot status
            BotStatusEntity botStatusEntity = new BotStatusEntity();
            botStatusEntity.setUid(importBotRequestObject.getFb_id());
            botStatusEntity.setStatus(importBotRequestObject.getStatus());
            botStatusEntity.setCreatedAt(importBotRequestObject.getBot_status_created_at());
            botStatusEntity.setUpdatedAt(importBotRequestObject.getBot_status_updated_at());
            // new bot ip
            BotIpEntity botIpEntity = new BotIpEntity();
            botIpEntity.setUid(importBotRequestObject.getFb_id());
            botIpEntity.setIp(importBotRequestObject.getIp());
            botIpEntity.setSubnet(importBotRequestObject.getSubnet());
            botIpEntity.setGateway(importBotRequestObject.getGateway());

            // new bot crawler entity
            BotCrawlerEntity botCrawlerEntity = new BotCrawlerEntity();
            botCrawlerEntity.setUid(importBotRequestObject.getFb_id());
            botCrawlerEntity.setCookie(importBotRequestObject.getCookie());
            botCrawlerEntity.setFbDtsg(importBotRequestObject.getFb_dtsg());
            botCrawlerEntity.setCreatedAt(importBotRequestObject.getBot_crawler_created_at());
            botCrawlerEntity.setUpdatedAt(importBotRequestObject.getBot_crawler_updated_at());
            List<BotCrawlerEntity>  botCrawlerEntities = new LinkedList<>();
            botCrawlerEntities.add(botCrawlerEntity);

            // RelationShip foreign key
            botProfileEntity.setBotCrawlersByUid(botCrawlerEntities);
            botProfileEntity.setBotIpByUid(botIpEntity);
            botProfileEntity.setBotStatusByUid(botStatusEntity);
            botStatusEntity.setBotProfileByUid(botProfileEntity);
            botIpEntity.setBotProfileByUid(botProfileEntity);
            botCrawlerEntity.setBotProfileByUid(botProfileEntity);

            // Update to database
            botProfileRepository.saveAndFlush(botProfileEntity);
            botIPRepository.saveAndFlush(botIpEntity);
            botCrawlerRepository.saveAndFlush(botCrawlerEntity);
            botStatusRepository.saveAndFlush(botStatusEntity);
            return true ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    private final String[] bot_status_type = {"", "new", "crawler", "checkpoint", "locked"} ;
    public JsonArray loadBotByStatus(Integer status, Integer number){
        try {
            JsonArray data = new JsonArray();
            List<BotStatusEntity> list = botStatusRepository.getAllByStatus(status);

            for (int i = 0 ; i < Math.min(list.size(), number); i++){
                BotStatusEntity botStatusEntity = list.get(i);
                data.add(returnHelper.formStatusSuccess(list.get(i).getUid(), bot_status_type[botStatusEntity.getStatus()]));
            }
            return data ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  null ;
        }

    }

    public JsonArray loadPublicInfoBot(List<String> infoParam , List<String> list_id){
        try {
            JsonArray data = new JsonArray();
            for (String id : list_id){
                try {
                    JsonObject bot_data = new JsonObject();
                        BotProfileEntity botProfileEntity = botProfileRepository.findOne(id);
                    if (botProfileEntity != null && botProfileEntity.getUid() != null ) {
                        for (String param : infoParam) {
                            if (param.equals("status")) {
                                BotStatusEntity botStatusEntity = botProfileEntity.getBotStatusByUid();
                                Integer value = botStatusEntity.getStatus();
                                bot_data.addProperty(param, value);
                            } else {
                                String value = "";
                                if (param.equals("name")) {
                                    value = botProfileEntity.getName();
                                } else if (param.equals("uid")) {
                                    value = botProfileEntity.getUid();
                                } else if (param.equals("password")) {
                                    value = botProfileEntity.getPassword();
                                } else if (param.equals("birthday")) {
                                    value = botProfileEntity.getBirthday();
                                } else if (param.equals("phone")) {
                                    value = botProfileEntity.getPhone();
                                }
                                if (value != "") {
                                    bot_data.addProperty(param, value);
                                }
                            }
                        }
                    } else {
                        bot_data = returnHelper.formStatusFailed(id,"No bot is matching with your request");
                    }
                    data.add(bot_data);
                } catch (Exception e){
                    log.error(e.getMessage(),e);
                    data.add(returnHelper.formStatusFailed(id,"get info failed"));
                }
            }
            return data ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  null ;
    }

    public JsonArray loadProxyInfoBot(String uid){
        JsonArray output = new JsonArray();
        try {
            BotIpEntity botIp = botIPRepository.getOne(uid);
            if (botIp != null ){
                JsonObject data = new JsonObject();
                data.addProperty("id", uid);
                data.addProperty("ip", botIp.getIp());
                data.addProperty("subnet_mask", botIp.getSubnet());
                data.addProperty("gateway", botIp.getGateway());
                data.addProperty("proxy", botIp.getProxy());
                output.add(data);
            } else {
                output.add(returnHelper.formStatusFailed(uid,"No proxy info found"));
            }
            return output ;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  null ;
    }

    public JsonArray chargePassword(String uid , String oldPassword , String newPassword){
        try {
            JsonArray output = new JsonArray();
            JsonObject data ;
            BotProfileEntity botProfile = botProfileRepository.findOne(uid);
            if (botProfile != null ){
                if (botProfile.getPassword().equals(oldPassword)){
                    botProfile.setPassword(newPassword);
                    try {
                        botProfileRepository.saveAndFlush(botProfile);
                        data = returnHelper.formStatusSuccess(uid, "Password changed");
                    } catch (Exception e){
                        data = returnHelper.formStatusFailed(uid,"Error change");
                    }
                } else {
                    data = returnHelper.formStatusFailed(uid,"Old password incorrect") ;
                }
            } else {
                data = returnHelper.formStatusFailed(uid,"No bot is matching with your request");
            }
            output.add(data);
            return  output;
        }catch (Exception e){
            return  null ;
        }
    }

    public JsonArray generatePassword(String uid){
        try{
            JsonArray output = new JsonArray();
            JsonObject data = new JsonObject();
            Random random =new Random();
            BotProfileEntity botProfile;
            botProfile = botProfileRepository.findOne(uid);
            if (botProfile== null){
                data = returnHelper.formStatusFailed(uid, "No bot is matching with your request");
                output.add(data);
                return output;
            }
            String name = botProfile.getName();
            name = NlpUtils.removeAccent(name);
            String phone = NlpUtils.removeAccent(botProfile.getPhone());
            String password = name.toLowerCase() + phone.toLowerCase() ;
            password = password.replaceAll("[^A-Za-z0-9]","a");
            while (password.length() > 8){
                int vt = random.nextInt(password.length());
                StringBuilder sb = new StringBuilder(password);
                sb.deleteCharAt(vt);
                password = sb.toString();
            }
            password += "@" ;
            data.addProperty("id", uid);
            data.addProperty("password", password);
            output.add(data);
            return output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null ;
        }
    }
    public JsonArray saveInfoBot(String uid, String fb_dtsg, String cookie){
        try {
            JsonArray output= new JsonArray();
            BotProfileEntity botProfileEntity = botProfileRepository.findOne(uid);
            if (botProfileEntity != null ) {
                BotCrawlerEntity botCrawler = new BotCrawlerEntity();
                botCrawler.setUid(uid);
                botCrawler.setFbDtsg(fb_dtsg);
                botCrawler.setCookie(cookie);
                botCrawler.setBotProfileByUid(botProfileEntity);
                botCrawlerRepository.saveAndFlush(botCrawler);
                JsonObject data = returnHelper.formStatusSuccess(uid, "Updated success");
                output.add(data);
            } else {
                output.add(returnHelper.formStatusFailed(uid,"No bot is matching with your request"));
            }
            return  output ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null ;
        }
    }
    public JsonObject login(String username, String userpassword){
        try {
            UsersEntity user = userRepository.findFirstByUserLoginAndUserPassword(username, userpassword);
            if (user != null ) {
                JsonObject data = new JsonObject();
                data.addProperty("code","0");
                data.addProperty("message","Login success");
                data.addProperty("user_action", user.getId());
                return data ;
            } else {
                JsonObject data = new JsonObject();
                data.addProperty("code","1");
                data.addProperty("message", "Username or Password incorrect") ;
                return data ;
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null ;
        }
    }

    public JsonArray queryOtpSms(String uid){
        try{
            JsonArray output = new JsonArray();
            Timestamp timeNow = new Timestamp(new Date().getTime()) ;
            long startTime = System.currentTimeMillis() - 30 * Timer.ONE_MINUTE ;
            Timestamp stTime = new Timestamp(startTime);
//            List<BotOtpEntity> otpEntities = botOtpRepository.getBotOtpEntitiesByUidAndCreatedAtBetween(uid,stTime, timeNow);
            List<BotOtpEntity> otpEntities = botOtpRepository.getAllByUidAndCreatedAtAfter(uid,stTime);
            if (otpEntities != null ){
                for (BotOtpEntity otpEntity : otpEntities){
                    try {
                        String id = otpEntity.getUid();
                        int otp = -1 ;
                        if (otpEntity.getOtpSms() != null )
                            otp = otpEntity.getOtpSms();
                        String updateTime = otpEntity.getCreatedAt().toString();
                        JsonObject data = new JsonObject();
                        data.addProperty("id", id);
                        data.addProperty("otp_sms", otp);
                        data.addProperty("updated_time", updateTime);
                        output.add(data);
                    } catch (Exception e){
                        log.error(e.getMessage(),e);
                    }
                }
                return output;
            } else {
                return output;
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null ;
        }
    }

//    public JsonArray updateStatus(String uid, int status, Timestamp unlockedAt){
//        JsonArray output= new JsonArray();
//        try{
//            BotStatusEntity botStatusEntity = botStatusRepository.findOne(uid);
//            if (botStatusEntity != null ) {
//                botStatusEntity.setStatus(status);
//                if (status == 4)
//                    botStatusEntity.setUnlockedAt(unlockedAt);
//
//                botStatusRepository.saveAndFlush(botStatusEntity);
//                JsonObject data = returnHelper.formStatusSuccess(uid, "Updated success");
//                output.add(data);
//            } else {
//                JsonObject data = returnHelper.formStatusFailed(uid, "No bot is matching with your request") ;
//                output.add(data);
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//            JsonObject data = returnHelper.formStatusFailed(uid,"Update failed");
//            output.add(data);
//        }
//        return output ;
//    }
    public JsonArray updateStatusBot(String uid, Integer old_status , Integer new_status , Integer user_action , Timestamp updated_time, Timestamp unlockedAt){
        JsonArray output = new JsonArray();
        try {
            BotProfileEntity botProfileEntity = botProfileRepository.findOne(uid);
            BotStatusEntity botStatusEntity = botStatusRepository.findOne(uid);
            if (botProfileEntity != null && botStatusEntity != null) {
                UsersEntity usersEntity = userRepository.findOne(user_action);
                if (usersEntity != null ) {
                    BotHistoryEntity botHistoryEntity = new BotHistoryEntity();
                    botHistoryEntity.setOldStatus(old_status);
                    botHistoryEntity.setNewStatus(new_status);
                    botHistoryEntity.setUid(uid);
                    botHistoryEntity.setUpdatedAt(updated_time);
                    botHistoryEntity.setUserAction(user_action);
                    botHistoryEntity.setBotProfileByUid(botProfileEntity);
                    botHistoryEntity.setUsersByUserAction(usersEntity);
                    botHistoryRepository.saveAndFlush(botHistoryEntity);

                    //....
                    botStatusEntity.setStatus(new_status);
                    if (new_status == 4)
                        botStatusEntity.setUnlockedAt(unlockedAt);
                    botStatusRepository.saveAndFlush(botStatusEntity);
                    //...
                    output.add(returnHelper.formStatusSuccess(uid, "Updated success"));
                } else {
                    output.add(returnHelper.formStatusFailed(uid,"No user_action is matching with your request"));
                }
            } else {
                output.add(returnHelper.formStatusFailed(uid,"No bot is matching with your request"));
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
            output.add(returnHelper.formStatusFailed(uid,"Update failed"));
        }
        return  output;
    }
    public JsonArray updateJobStatus(Integer job_id, Boolean is_completed, Integer user_action, Timestamp updated_time){
        JsonArray output = new JsonArray();
        try {
            JobBotEntity jobBotEntity = jobBotRepository.findOne(job_id);
            if (jobBotEntity != null){
                if (is_completed)
                    jobBotEntity.setIsCompleted((byte)1);
                else
                    jobBotEntity.setIsCompleted((byte)0);
                jobBotEntity.setUserAction(user_action);
                jobBotEntity.setUpdatedAt(updated_time);
                UsersEntity usersEntity = userRepository.findOne(user_action);
                if (usersEntity != null){
                    jobBotEntity.setUsersByUserAction(usersEntity);
                    jobBotRepository.saveAndFlush(jobBotEntity);
                    output.add(returnHelper.formStatusSuccess("job_id", job_id, "Updated success"));
                } else {
                    output.add(returnHelper.formStatusFailed("user_action", user_action, "No user_action is matching with your request"));
                }

            } else {
                output.add(returnHelper.formStatusFailed("job_id", job_id, "No job is matching with your request"));
            }

        } catch (Exception e){
            log.error(e.getMessage(),e);
            output.add(returnHelper.formStatusFailed("job_id",job_id,"Update failed"));
        }
        return  output;
    }
    public JsonArray queryJob(Integer user_action){
        JsonArray output = new JsonArray();
        try {
                if (userRepository.exists(user_action)) {
                    UsersEntity usersEntity = userRepository.findOne(user_action);
                    JobBotEntity jobBotEntity = jobBotRepository.getFirstByIsProcessed((byte) 0);
                    if (jobBotEntity != null) {
                        JsonObject data = new JsonObject();
                        data.addProperty("job_id", jobBotEntity.getJobId());
                        data.addProperty("uid", jobBotEntity.getUid());
                        data.addProperty("job_type", jobBotEntity.getJobType());
                        data.addProperty("create_at", jobBotEntity.getCreatedAt().toString());
                        jobBotEntity.setIsProcessed((byte) 1);
                        jobBotEntity.setUserAction(user_action);
                        jobBotEntity.setUsersByUserAction(usersEntity);
                        jobBotRepository.saveAndFlush(jobBotEntity);
                        output.add(data);
                    }
                } else {
                    output.add(returnHelper.formStatusFailed("user_action", user_action,"No user_action is matching with your request"));
                }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  output ;
    }
}
