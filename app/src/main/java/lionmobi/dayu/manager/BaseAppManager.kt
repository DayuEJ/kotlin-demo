package lionmobi.dayu.manager

import lionmobi.dayu.mode.RunningAppInfo
import java.util.ArrayList

class BaseAppManager{
    companion object{
        var sInstance: BaseAppManager? = null;
        fun getInstance() =
                sInstance ?: synchronized(this) {
                    sInstance ?: BaseAppManager().also { sInstance = it }
                }
    }
}