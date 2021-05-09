package com.juntai.wisdom.inspection.home_page.baseinspect;

import com.haibin.calendarview.BaseView;
import com.juntai.disabled.basecomponent.mvp.IView;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:41
 */
public interface BaseInspectContract {

     String REMARK = "备注";
     String IMPORTANTOR_TAB_TITLES = "tab标题";
     String IMPORTANTOR_TAB_TITLE_ALL = "全部";
     String IMPORTANTOR_TAB_TITLE_TO_VISITE = "待走访";
     String IMPORTANTOR_TAB_TITLE_VISITED = "已走访";
     String IMPORTANTOR_TAB_TITLE_TO_CHECK = "待检查";
     String IMPORTANTOR_TAB_TITLE_REPAIRING = "整改中";
     String IMPORTANTOR_TAB_TITLE_IS_OK = "已合格";
     String INSPECTION_SITE = "巡检点";
     String INSPECTION_ADDR = "巡检地址";
     String INSPECTION_RESPONSIBLE = "安全责任人";
     String INSPECTION_UNIT_UCC = "社会信用代码";
     String INSPECTION_TEL = "联系电话";
     String INSPECTION_RESPONSIBLE_TEL = INSPECTION_RESPONSIBLE + INSPECTION_TEL;
     String INSPECTION_SPARE_PERSON = "备用联系人";
     String INSPECTION_SPARE_PERSON_TEL = INSPECTION_SPARE_PERSON + INSPECTION_TEL;
     String INSPECTION_CHECK_TYPE = "检查类型";
     String INSPECTION_CHECK_RECORD = "检查记录";
     String INSPECTION_RESPONSIBILITY = "责任书";
     String INSPECTION_RECTIFY_NOTICE = "整改通知书";
     String INSPECTION_WORKER = "从业人员";
     String INSPECTION_CHECK_PROBLEMS = "问题及巡查情况";
     String INSPECTION_VISIT_RECORD = "走访记录";
     String INSPECTION_SECURITY_RECORD = "治安巡检记录";
     String INSPECTION_UNIT_NAME = "单位名称";
     String INSPECTION_UNIT_ADDR = "单位地址";
     String INSPECTION_UNIT_LEGAL_PERSON = "单位法人";
     String INSPECTION_UNIT_LEGAL_PERSON_TEL = INSPECTION_UNIT_LEGAL_PERSON + INSPECTION_TEL;
     String INSPECTION_UNIT_TYPE = "单位类型";

    interface IInspectView extends IView {
    }

    interface IInspectPresent {
        void searchCompanys(RequestBody requestBody, String tag);

        /**
         * 搜索添加
         * @param requestBody
         * @param tag
         */
        void searchAddUnit(RequestBody requestBody, String tag);

        /**
         * 手动添加
         * @param requestBody
         * @param tag
         */
        void manualAddUnit(RequestBody requestBody, String tag);
        void checkUnitUnique(RequestBody requestBody, String tag);
        void getUnitType(RequestBody requestBody, String tag);
    }
}
