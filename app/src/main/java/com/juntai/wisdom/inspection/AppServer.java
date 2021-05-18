package com.juntai.wisdom.inspection;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.importantor.AllImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.AllInspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.UserBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {
    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @POST(AppHttpPath.LOGIN)
    Observable<UserBean> login(@Query("account") String account, @Query("password") String password);

    /**
     * 上传轨迹
     */
    @POST(AppHttpPath.USER_HISTORY_UPLOAD)
    Observable<BaseResult> uploadHistory(@Query("account") String account, @Query("token") String token, @Query(
            "userId") int userId,
                                         @Query("source") int source, @Query("json") String json);

    /**
     * 用户个人信息
     *
     * @return
     */
    @POST(AppHttpPath.GET_USER_INFO)
    Observable<UserBean> getUserInfo(@Body RequestBody requestBody);

    /**
     * 退出登录
     *
     * @return
     */
    @POST(AppHttpPath.LOGIN_OUT)
    Observable<BaseResult> loginOut(@Body RequestBody requestBody);

    /**
     * 单位类型
     *
     * @return
     */
    @POST(AppHttpPath.UNIT_TYPE)
    Observable<IdNameBean> getUnitType(@Body RequestBody requestBody);


    /**
     * 添加单位搜索
     *
     * @return
     */
    @POST(AppHttpPath.SEARCH_COMPANYS)
    Observable<SearchedUnitsBean> searchCompanys(@Body RequestBody requestBody);

    /**
     * 搜索添加
     *
     * @return
     */
    @POST(AppHttpPath.SEARCH_ADD_UNIT)
    Observable<BaseResult> searchAddUnit(@Body RequestBody requestBody);

    /**
     * 手动添加单位
     *
     * @return
     */
    @POST(AppHttpPath.MANUAL_ADD_UNIT)
    Observable<BaseResult> manualAddUnit(@Body RequestBody requestBody);


    /**
     * 查询单位  信用码 是否存在   唯一性
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.CHECK_UNIT_UNIQUE)
    Observable<BaseResult> checkUnitUnique(@Body RequestBody requestBody);

    /**
     * 治安消防模块首页搜索
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_UNIT_TO_CHECK)
    Observable<SearchedUnitsBean> searchUnitFromFireInspection(@Body RequestBody requestBody);



    /*==============================================  巡检点  =============================================*/

    /**
     * 治安巡检点详情
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.INSPECTION_SITE_DETAIL)
    Observable<InspectionSiteBean> getInspectionSiteDetail(@Body RequestBody requestBody);

    /**
     * 搜索所有的巡检点  待添加
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_INSPECTION_SITES_TO_ADD)
    Observable<AllInspectionSiteBean> searchInspectionSitesToAdd(@Body RequestBody requestBody);

    /**
    /**
     * 搜索已经添加的巡检点
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_ADDED_INSPECTION_SITES)
    Observable<AllInspectionSiteBean> searchInspectionSitesAdded(@Body RequestBody requestBody);

    /**
     *查询巡检点是否存在
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.CHECK_INSPECTION_SITE_UNIQUE)
    Observable<BaseResult> checkInspectionSiteNameUnique(@Body RequestBody requestBody);

    /**
     * 搜索添加治安巡检点
     *
     * @return
     */
    @POST(AppHttpPath.SEARCH_ADD_INSP_SITE)
    Observable<BaseResult> searchAddInspectSite(@Body RequestBody requestBody);


    /**
     * 手动添加治安巡检点
     *
     * @return
     */
    @POST(AppHttpPath.MANUAL_ADD_INSP_SITE)
    Observable<BaseResult> manualAddInspectSite(@Body RequestBody requestBody);
    /**
     * 手动添加治安巡检点
     *
     * @return
     */
    @POST(AppHttpPath.ADD_INSPECTION_RECORD)
    Observable<BaseResult> addInspectionRecord(@Body RequestBody requestBody);

    /**
     * 巡检记录列表
     *
     * @return
     */
    @POST(AppHttpPath.SECURITY_INSPECT_RECORDS)
    Observable<SecurityInspectRecordListBean> getSecurityInspectRecords(@Body RequestBody requestBody);

    /**
     * 巡检记录详情
     *
     * @return
     */
    @POST(AppHttpPath.SECURITY_INSPECT_RECORD_DETAIL)
    Observable<SecurityInspectRecordDetailBean> getSecurityInspectRecordDetail(@Body RequestBody requestBody);

    /**
     * 治安巡查问题及情况类型
     *
     * @return
     */
    @POST(AppHttpPath.SECURITY_INSPECT_QUESTION)
    Observable<IdNameBean> getInspectQuestions(@Body RequestBody requestBody);




    /*==============================================  重点人员  =============================================*/

    /**
     * 重点人员详情
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.IMPORTANTOR_DETAIL)
    Observable<ImportantorBean> getImportantorDetail(@Body RequestBody requestBody);

    /**
     * 搜索所有的重点人员  待添加
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_IMPORTANTOR_TO_ADD)
    Observable<AllImportantorBean> searchImportantorToAdd(@Body RequestBody requestBody);

    /**
     *查询重点人员id是否存在
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.CHECK_IMPORTANTOR_UNIQUE)
    Observable<BaseResult> checkImportantorIDUnique(@Body RequestBody requestBody);

    /**
     * 搜索添加重点人员
     *
     * @return
     */
    @POST(AppHttpPath.SEARCH_ADD_IMPORTANTOR)
    Observable<BaseResult> searchAddImportantor(@Body RequestBody requestBody);


    /**
     * 手动添加重点人员
     *
     * @return
     */
    @POST(AppHttpPath.MANUAL_ADD_IMPORTANTOR)
    Observable<BaseResult> manualAddImportantor(@Body RequestBody requestBody);


    /**
     * 人员类型
     *
     * @return
     */
    @POST(AppHttpPath.IMPORTANTOR_TYPES)
    Observable<IdNameBean> getImportantorTypes(@Body RequestBody requestBody);
    /**
     * 人员状态
     *
     * @return
     */
    @POST(AppHttpPath.IMPORTANTOR_STATUS)
    Observable<IdNameBean> getImportantorStatus(@Body RequestBody requestBody);


    /**
     * 重点人员模块首页搜索
     *
     * @return
     */
    @POST(AppHttpPath.SEARCH_ALL_ADDED_IMPORTANTOR)
    Observable<AllImportantorBean> getAllAddedImportantor(@Body RequestBody requestBody);


    /**
     * 走访问题及情况类型
     *
     * @return
     */
    @POST(AppHttpPath.VISIT_QUESTIONS)
    Observable<IdNameBean> getVisitQuestions(@Body RequestBody requestBody);





}