package lionmobi.dayu.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import lionmobi.dayu.myapplication.R


class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView(){
        bindClicks(this, R.id.ly_junk_clean, R.id.ly_battery_save)
    }

    //这里java switch 语法 转换为kotlin when 条件语句
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ly_junk_clean ->{
                startActivity(Intent(this@MainActivity, JunkCleanActivity::class.java))
            }

            R.id.ly_battery_save ->{
                startActivity(Intent(this@MainActivity, BatterySaveActivity::class.java))
            }

            R.id.ly_game_boost->{

            }
        }
    }
}