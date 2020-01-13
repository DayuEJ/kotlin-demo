package lionmobi.dayu.activity

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import java.util.*

//一个类想派生子类必须用open 修饰
open class BaseActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLanguage()
    }

        fun initLanguage(){
        var locale : Locale = Locale(Locale.getDefault().language)
        var config : Configuration = resources.configuration
        config.locale = locale;
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    //可变参数用vararg 修饰
    fun bindClicks(listener : View.OnClickListener, vararg ids: Int){
        //for in 循环  for int i in x  or for int i in  x unit y
        for (id in ids){
            findViewById<View>(id).setOnClickListener(listener)
        }
    }
}