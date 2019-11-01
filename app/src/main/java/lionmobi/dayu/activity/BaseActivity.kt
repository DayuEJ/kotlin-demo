package lionmobi.dayu.activity

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import java.util.*

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

    fun bindClicks(listener : View.OnClickListener, ){

    }
}