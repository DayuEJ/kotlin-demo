package lionmobi.dayu.manager

import android.os.Parcelable
import com.tencent.mmkv.MMKV

//kotlin 单例模式  constructor() 为LocalStorageManager 的主构造器
class LocalStorageManager {

    //companion object kotlin 伴生对象  apply{this 指代自己}  also{it 指代自己}
    companion object {
        lateinit var mkv: MMKV
        var sInstance: LocalStorageManager? = null;
        fun getInstance() =
                sInstance ?: synchronized(this) {
                    sInstance ?: LocalStorageManager().also { sInstance = it }
                }
    }

    constructor() {
        mkv = MMKV.defaultMMKV()
    }

    fun getKv(): MMKV {
        if (mkv == null) {
            mkv = MMKV.defaultMMKV()
        }
        return mkv
    }

    fun hasKey(key: String): Boolean {
        return getKv().containsKey(key)
    }

    fun setBoolean(key: String, value: Boolean) {
        getKv().encode(key, value)
    }

    fun getAndReverse(key: String, defaultValue: Boolean): Boolean {
        val result = getBoolean(key, defaultValue)
        setBoolean(key, !result)
        return result
    }

    fun setInt(key: String, value: Int) {
        getKv().encode(key, value)
    }

    fun getAndIncrease(key: String): Int {
        val result = getInt(key, 0)
        var temp = result
        setInt(key, ++temp)
        return result
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getKv().decodeBool(key, defaultValue)
    }

    fun setLong(key: String, value: Long) {
        getKv().encode(key, value)
    }

    fun setFloat(key: String, value: Float) {
        getKv().encode(key, value)
    }

    fun setString(key: String, value: String) {
        getKv().encode(key, value)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return getKv().decodeFloat(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return getKv().decodeInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return getKv().decodeLong(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String): String {
        return getKv().decodeString(key, defaultValue)
    }

    fun getStringSet(key: String, defaultValues: Set<String>): Set<String> {
        return getKv().decodeStringSet(key, defaultValues)
    }

    fun <T : Parcelable> setParcelable(key: String, value: T) {
        getKv().encode(key, value)
    }

    fun <T : Parcelable> getParcelable(key: String, defaultValue: Class<T>): T {
        return getKv().decodeParcelable(key, defaultValue)
    }
}