package lionmobi.dayu.util

import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit

class HttpUtils {
    companion object{
        private var httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        fun postHttpRequest(url : String, params : Map<String, Any>){

        }
    }
}