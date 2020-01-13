package lionmobi.dayu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lionmobi.dayu.mode.User
import lionmobi.dayu.myapplication.R

class ListAdapterActivity : BaseActivity(){
    var mDataList = mutableListOf<User>();//相当于创建一个空的集  mutableListOf<User>()
    var mCacheList = ArrayList<User>();
    lateinit var adapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
    }

    fun loadData(){
        GlobalScope.launch (Dispatchers.Main){
            for (i in 1 until 5){
                val user = User("ljy" + i, 18);
                mDataList.add(user);
            }
            adapter = MyAdapter();
//            list_view.adapter = adapter;
            adapter.notifyDataSetChanged();
        }
    }

    inner class MyAdapter : BaseAdapter(){
        override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
            var view = convertView
            var holder : ViewHolder
            if (view == null){
                view = LayoutInflater.from(this@ListAdapterActivity).inflate(R.layout.layout_notify_advance_time_item, null)
                holder = ViewHolder()
                holder.tv_info =  view!!.findViewById(R.id.tv_info) as TextView
            }else{
                holder = view!!.tag as ViewHolder
            }
            val user : User = getItem(p0) as User
            holder.tv_info.text = user.name
            return view!!;
        }

        override fun getItem(p0: Int): Any {
            return mDataList.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mDataList.size
        }

        inner class ViewHolder{
            lateinit var tv_info : TextView
        }
    }
}