package viettel.vtcc.crawler.fb_bot.manage.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionParamObject {
    String function ;
    List<Info_param> info_parameter ;
    List<Uid> list_ids ;
    String uid ;
    String old_password ;
    String new_password ;
    String fb_dtsg ;
    String cookie ;
    Integer old_status ;
    Integer new_status ;
    Integer user_action;
    Integer job_id ;
    Boolean is_completed;
    String user_login ;
    String user_password ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updated_time ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp unlocked_time ;
    public List<String> getParam(){
        try {
            List<String> output = new ArrayList<>();
            for (Info_param infor : info_parameter){
                output.add(infor.getInfo_query());
            }
            return  output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null ;
    }
    public List<String> getListID(){
        try {
            List<String> output = new ArrayList<>();
            for (Uid id : list_ids){
                output.add(id.uid);
            }
            return  output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null ;
    }

}
