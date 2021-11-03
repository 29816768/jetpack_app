package com.wyq.kotlinjetpackdemo.http

import com.wyq.kotlinjetpackdemo.bean.ArticleBean
import com.wyq.kotlinjetpackdemo.ui.collect.CollectBean
import com.wyq.kotlinjetpackdemo.ui.integral.IntegralRecordBean
import com.wyq.kotlinjetpackdemo.ui.main.home.BannerBean
import com.wyq.kotlinjetpackdemo.ui.main.mine.IntegralBean
import com.wyq.kotlinjetpackdemo.ui.main.square.system.SystemBean
import com.wyq.kotlinjetpackdemo.ui.main.tab.TabBean
import com.wyq.kotlinjetpackdemo.ui.login.UserBean
import com.wyq.kotlinjetpackdemo.ui.my.MyArticleEntity
import com.wyq.kotlinjetpackdemo.ui.rank.RankBean
import retrofit2.http.*

/**
 * @date 2020/5/9
 * @author zs
 */
interface ApiService {
    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeList(@Path("page") pageNo: Int): ApiResponse<ArticleBean>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    suspend fun getTopList(): ApiResponse<MutableList<ArticleBean.DatasBean>>

    /**
     * banner
     */
    @GET("/banner/json")
    suspend fun getBanner(): ApiResponse<MutableList<BannerBean>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): ApiResponse<UserBean>

    @GET("/user/logout/json")
    suspend fun logout(): ApiResponse<Any>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectData(@Path("page") pageNo: Int):
            ApiResponse<CollectBean>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun getIntegral(): ApiResponse<IntegralBean>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): ApiResponse<Any>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): ApiResponse<Any>

    /**
     * 获取项目tab
     */
    @GET("/project/tree/json")
    suspend fun getProjectTabList(): ApiResponse<MutableList<TabBean>>

    /**
     * 获取项目tab
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getAccountTabList(): ApiResponse<MutableList<TabBean>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{pageNum}/json")
    suspend fun getProjectList(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : ApiResponse<ArticleBean>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    suspend fun getAccountList(@Path("id") cid: Int, @Path("pageNum") pageNum: Int)
            : ApiResponse<ArticleBean>


    /**
     * 获取项目tab
     */
    @POST("/article/query/{pageNum}/json")
    suspend fun search(@Path("pageNum") pageNum: Int, @Query("k") k: String)
            : ApiResponse<ArticleBean>

    /**
     * 体系
     */
    @GET("/tree/json")
    suspend fun getSystemList(): ApiResponse<MutableList<SystemBean>>


    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{pageNum}/json")
    suspend fun getSystemArticle(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : ApiResponse<ArticleBean>


    /**
     * 导航
     */

//    @GET("/navi/json")
//    fun getNavigation(): ApiResponse<MutableList<NavigationEntity>>


    /**
     * 排名
     */

    @GET("/coin/rank/{pageNum}/json")
    suspend fun getRank(@Path("pageNum") pageNum: Int): ApiResponse<RankBean>


    /**
     * 积分记录
     */

    @GET("/lg/coin/list/{pageNum}/json")
    suspend fun getIntegralRecord(@Path("pageNum") pageNum: Int): ApiResponse<IntegralRecordBean>


    /**
     * 我分享的文章
     */

    @GET("/user/lg/private_articles/{pageNum}/json")
    suspend fun getMyArticle(@Path("pageNum") pageNum: Int): ApiResponse<MyArticleEntity>


    /**
     * 我分享的文章
     */
    @POST("/lg/user_article/delete/{id}/json")
    suspend fun deleteMyArticle(@Path("id") id: Int): ApiResponse<Any>

    /**
     * 分享文章
     */
    @POST("/lg/user_article/add/json")
    suspend fun publishArticle(@Query("title") title: String, @Query("link") link: String)
            : ApiResponse<Any>

    /**
     * 注册
     */
    @POST("/user/register")
    suspend fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String,
    ): ApiResponse<Any>
}