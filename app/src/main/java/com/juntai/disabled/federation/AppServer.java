package com.juntai.disabled.federation;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.BaseStreamBean;
import com.juntai.disabled.federation.bean.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {
    /**
     * 登录
     *
     * @param account
     * @param password
     * @param weChatId
     * @param qqId
     * @return
     */
    @POST(AppHttpPath.LOGIN)
    Observable<UserBean> login(@Query("account") String account, @Query("password") String password,
                               @Query("weChatId") String weChatId, @Query("qqId") String qqId);

    /**
     * 上传轨迹
     */
    @POST(AppHttpPath.USER_HISTORY_UPLOAD)
    Observable<BaseResult> uploadHistory(@Query("account") String account, @Query("token") String token, @Query(
            "userId") int userId,
                                         @Query("source") int source, @Query("json") String json);


}