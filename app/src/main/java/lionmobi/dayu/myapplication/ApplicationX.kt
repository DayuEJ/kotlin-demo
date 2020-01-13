package lionmobi.dayu.myapplication

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import lionmobi.dayu.manager.LocalStorageManager

class ApplicationX : Application(){

    companion object{
        lateinit var sInstance : ApplicationX;
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this;
        initLocal();
        if (isMainProcess(this)){
            doInitWork();
        }
    }


    fun initLocal(){
        MMKV.initialize(this)
        LocalStorageManager.getInstance();
    }

    fun doInitWork(){
        initSync();
        initAsync();
    }

    fun initAsync(){

    }

    fun initSync(){

    }

    fun isMainProcess(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = activityManager.runningAppProcesses
        if (list != null) {
            for (appProcess in list) {
                if (appProcess.processName == context.packageName) {
                    if (appProcess.pid == android.os.Process.myPid()) {
                        return true
                    }
                }
            }
            return false
        }
        return true
    }
}