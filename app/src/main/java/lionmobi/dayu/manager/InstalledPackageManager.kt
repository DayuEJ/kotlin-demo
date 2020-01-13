package lionmobi.dayu.manager

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import lionmobi.dayu.myapplication.ApplicationX
import kotlin.collections.ArrayList

class InstalledPackageManager{
    private val mPackageIncludeUninstallMutex = Any() //Any() 相当于java object
    private val mPackages = ArrayList<PackageInfo>()
    
    companion object{
        var sInstance : InstalledPackageManager? = null;
        fun getInstance() : InstalledPackageManager? {
            if (sInstance === null){
                synchronized(this){
                    if (sInstance == null){
                        sInstance = InstalledPackageManager();
                    }
                }
            }
            return sInstance;
        }
    }


    fun getPackageInfoList(filterUnInstall : Boolean) : ArrayList<PackageInfo> {
        var context = ApplicationX.sInstance;
        var packageManager = context.packageManager;
        sInstance?.let {
            synchronized(it) {
                if (mPackages.size < 0) {
                    try {
                        mPackages.clear()
                        //java filter ? x : y  kotlin if(filterUnInstall) x else y
                        mPackages.addAll(packageManager.getInstalledPackages(if (filterUnInstall) PackageManager.GET_ACTIVITIES else 0))
                    } catch (e: Exception) {
                        try {
                            mPackages.clear()
                            mPackages.addAll(packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES))
                        } catch (e1: Exception) {
                            e1.printStackTrace()
                        }
                    }
                }
            }
        }
        //kotlin 强转 as
        return mPackages.clone() as ArrayList<PackageInfo>;
    }

    fun getPackageInfoListWhenInit(filterUnInstall: Boolean): ArrayList<PackageInfo> {
        getPackageInfoListWhenInit(filterUnInstall)
        return mPackages.clone() as ArrayList<PackageInfo>
    }
}