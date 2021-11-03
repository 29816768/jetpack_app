package com.wyq.kotlinjetpackdemo.ui.login

import androidx.lifecycle.MutableLiveData
import com.wyq.kotlinjetpackdemo.constants.Constants
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.zs.base_library.base.BaseRepository
import com.zs.base_library.http.ApiException
import com.zs.base_library.utils.PrefUtils
import com.zs.zs_jetpack.event.LoginEvent
import kotlinx.coroutines.CoroutineScope
import org.greenrobot.eventbus.EventBus

/**
 * des 登陆
 * @date 2020/7/9
 * @author zs
 */
class LoginRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    fun login(username: String, password: String,loginLiveData : MutableLiveData<UserBean>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .login(username,password)
                    .data()
            },
            success = {
                //登陆成功保存用户信息，并发送消息
                PrefUtils.setObject(Constants.USER_INFO,it)
                //更改登陆状态
                PrefUtils.setBoolean(Constants.LOGIN,true)
                //发送登陆消息
                EventBus.getDefault().post(LoginEvent())
                loginLiveData.postValue(it)
            }
        )
    }

}