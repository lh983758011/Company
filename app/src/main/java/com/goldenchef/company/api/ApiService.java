package com.goldenchef.company.api;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luo-hao on 2017-03-07.
 */

public interface ApiService {

    String BASE_URL = "http://139.196.232.166:8081/goldchef/";

//    String BASE_URL = "http://m2.beacool.com:8080/mozan-1.0-SNAPSHOT/";

    String LOGIN_RUL = "companyuser/login.do";
    String SENDMES_URL = "sendMsg/send.do";
    String PUBLISH_JOB = "pubjobs/add.do";
    String SELECT_JOBS = "select.do";
    String SELECT_JOB_TYPE = "selectJobs.do";

    /**
     * 登录
     *
     * @param phoneNum 电话号码
     * @param password 密码
     * @param code     验证码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_RUL)
    Observable<String> login(@Field("phoneNum") String phoneNum, @Field("password") String password, @Field("smsValidateCode") String code);

    /**
     * 发送验证码
     *
     * @param phoneNum
     * @return
     */
    @GET(SENDMES_URL)
    Observable<String> getVerificationCod(@Query("phoneNum") String phoneNum);


    /**
     * 发布职位
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(PUBLISH_JOB)
    Observable<String> publishJob(@FieldMap Map<String, String> map);

    /**
     * 获取简历
     *
     * @param token
     * @param currentPage
     * @param pageSize
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST(SELECT_JOBS)
    Observable<String> selectJobs(@Field("token") String token, @Field("currentPage") int currentPage, @Field("pageSize") int pageSize, @Field("id") String id);

    /**
     * 获取工作类型
     *
     * @param jobId
     * @return
     */
    @GET(SELECT_JOB_TYPE)
    Observable<String> selectJobType(@Query("jobId") String jobId);
//
//    /**
//     *
//     * 获取工作类型
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("serviceRouting.do")
//    Observable<String> test(@FieldMap Map<String, Object> map);
}
