package com.juntai.wisdom.inspection;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.UserBean;
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
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.CHECK_UNIT_UNIQUE)
    Observable<BaseResult> checkUnitUnique(@Body RequestBody requestBody);
    /**
     * 治安消防模块首页搜索
     * @param requestBody
     * @return
     */
    @POST(AppHttpPath.SEARCH_UNIT_CHECK_STATUS)
    Observable<SearchedUnitsBean> searchUnitFromFireInspection(@Body RequestBody requestBody);

}