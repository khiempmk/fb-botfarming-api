package viettel.vtcc.crawler.fb_bot.manage.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import viettel.vtcc.crawler.fb_bot.manage.model.FunctionParamObject;
import viettel.vtcc.crawler.fb_bot.manage.model.ImportBotRequestObject;

@Slf4j
@Component
public class CheckRequireField {
    public boolean importAccountCheckFields(ImportBotRequestObject request){
        try {
            return true ;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  false ;
    }
    public boolean saveInfoBotCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("update_session"))
                return false ;
            if (!checkNull(param.getUid()) || !checkNull(param.getFb_dtsg()) || !checkNull(param.getCookie()))
                return  false;
            return  true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  false ;
    }
    public boolean loginCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("login"))
                return false ;
            if (!checkNull(param.getUser_login()) || !checkNull(param.getUser_password()) )
                return  false;
            return  true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  false ;
    }
    public boolean chargePasswordCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("update_password"))
                return false ;
            if (!checkNull(param.getOld_password()) || !checkNull(param.getNew_password()) || !checkNull(param.getUid()))
                return  false;
            return  true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  false;
    }
//    public boolean updateStatusCheckFields(FunctionParamObject param){
//        try {
//            if (param.getFunction() == null || !param.getFunction().equals("update_status"))
//                return false ;
//            if (!checkNull(param.getUid()) || param.getStatus() == null)
//                return  false;
//            if (param.getStatus() == 4 && param.getUnlocked_time() == null)
//                return false;
//            return  true;
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//        }
//        return  false;
//    }
    public boolean updateStatusBotCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("update_status_bot"))
                return false ;
            if (!checkNull(param.getUid()))
                return  false;
            if (param.getUser_action() == null || param.getOld_status() == null || param.getNew_status() == null  || param.getUpdated_time()== null)
                return false ;
            if (param.getNew_status() == 4 && param.getUnlocked_time() == null)
                return false;
            return  true;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return false ;
    }
    public boolean updateJobStatusCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("update_job"))
                return false ;
            if (param.getUser_action() == null || param.getJob_id() == null || param.getIs_completed() == null  || param.getUpdated_time()== null)
                return false ;
            return  true;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return false ;
    }
    public boolean loadPublicInfoBotCheckFields(FunctionParamObject param){
        try {
            if (param.getFunction() == null || !param.getFunction().equals("public_info"))
                return false ;
            if (param.getInfo_parameter() == null  && param.getList_ids() == null)
                return  false;
            return  true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  false;
    }
    private boolean checkNull(String s){
        if (s == null) return  false;
        if (s.equals("")) return  false ;
        return  true;
    }
}
