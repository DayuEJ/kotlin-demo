package lionmobi.dayu.white

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.inputmethod.InputMethodManager
import lionmobi.dayu.manager.InstalledPackageManager
import lionmobi.dayu.myapplication.ApplicationX
import java.util.ArrayList

class WhiteListManager{
    private val DEFAULT_PRODUCTS = getProductList()
    private val LAUNCHERS = getHomes()
    private val BROWSERS = WhiteList.SYSTEM_BROWSERS;
    private val SYSTEM_PACKAGES_WITH_IGNORE = getSystemPackages(true)
    private val SYSTEM_PACKAGES_WITHOUT_IGNORE = getSystemPackages(false)
    private var INPUT_METHODS: List<String>? = null


    fun getHomes() : MutableList<String>{
        val names = ArrayList<String>()
        //属性
        val intent = Intent("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.HOME")
        val packageManager = ApplicationX.sInstance.packageManager;
        val resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (ri in resolveInfo) {
            names.add(ri.activityInfo.packageName)
        }
        names.remove("com.android.settings")
        return names
    }
    /**
     * get filter list,never show in
     *
     * @return
     */
    fun getBasicFilterList(): MutableList<String> {
        val list = ArrayList<String>()
        if (INPUT_METHODS == null) {
            INPUT_METHODS = getInputMethodList()
        }
        list.addAll(INPUT_METHODS!!)
        list.addAll(WhiteList.GOOGLE_PRODUCT_LIST)
        list.addAll(WhiteList.OTHER_SYSTEM_APP_LIST)
        list.addAll(DEFAULT_PRODUCTS)
        list.addAll(LAUNCHERS)
        list.addAll(SYSTEM_PACKAGES_WITH_IGNORE)
        list.removeAll(BROWSERS)
        return list
    }

    /**
     * get filter list without ignore
     *
     * @return
     */
    fun getFullFilterList(): MutableList<String> {
        val list = ArrayList<String>()
        if (INPUT_METHODS == null) {
            INPUT_METHODS = getInputMethodList()
        }
        list.addAll(INPUT_METHODS!!)
        list.addAll(WhiteList.GOOGLE_PRODUCT_LIST)
        //list.addAll(WhiteList.OTHER_SYSTEM_APP_LIST);
        list.addAll(DEFAULT_PRODUCTS)
        list.addAll(LAUNCHERS)
        list.addAll(SYSTEM_PACKAGES_WITHOUT_IGNORE)
        list.removeAll(BROWSERS)
        list.addAll(WhiteList.MEMO_BOOST_FILTER)
        return list
    }

    /**
     * get important app list
     *
     * @return
     */
    fun getProtectedList(): MutableList<String> {
        val list = ArrayList<String>()
        list.addAll(WhiteList.PROTECTED_APP_LIST)
        list.addAll(WhiteList.SYSTEM_BROWSERS)
        return list
    }

    /**
     * get input method list
     *
     * @return
     */
    fun getInputMethodList(): List<String> {
        val inputMethodList = ArrayList<String>()
        try {
            val application = ApplicationX.sInstance
            val im = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val inputs = im.inputMethodList
            for (i in inputs.indices) {
                inputMethodList.add(inputs[i].packageName)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }

        return inputMethodList
    }

    /**
     * get system packages
     *
     * @return
     */
    fun getSystemPackages(ignoreRequire: Boolean): List<String> {
        val list = ArrayList<String>()
        val installList = InstalledPackageManager.getInstance()!!.getPackageInfoListWhenInit(false)
        val size = installList.size
        for (i in 0 until size) {
            val p = installList.get(i)
            val appInfo = p.applicationInfo
            if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM > 0) {
                if (ignoreRequire && RequireListConstant.REQUIRE_LIST.contains(appInfo.packageName)) {
                    continue
                }
                list.add(appInfo.packageName)
            }
        }
        return list
    }

    /**
     * @return
     */
    fun getProductList(): List<String> {
        val list = ArrayList<String>()
        list.add(ApplicationX.sInstance.getPackageName())
        return list
    }

    fun getBlackList(): List<String> {
        return WhiteList.SYSTEM_SERVICES
    }
}