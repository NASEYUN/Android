package com.example.holic

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var dayList : ArrayList<String> = ArrayList<String>()
    var mCal : Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val instance = Calendar.getInstance()
//        val year = instance.get(Calendar.YEAR).toString()
//        val month = (instance.get(Calendar.MONTH)+1).toString()
//        val date = instance.get(Calendar.DATE).toString()

        var currentTextView = findViewById<TextView>(R.id.currentTextView)

        var now = System.currentTimeMillis()
        var date : Date = Date(now)
        var curYearFormat : SimpleDateFormat = SimpleDateFormat("yyyy",Locale.KOREA)
        var curMonthFormat : SimpleDateFormat = SimpleDateFormat("MM",Locale.KOREA)
        var curDateFormat : SimpleDateFormat = SimpleDateFormat("dd",Locale.KOREA)

        currentTextView.setText(curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월")




//        var previous = findViewById<ImageView>(R.id.previousMonthButton)
//        previous.setOnClickListener {
//        }

        mCal.set(Integer.parseInt(curYearFormat.format(date)),Integer.parseInt(curMonthFormat.format(date)) - 1, 1)
        var dayNum = mCal.get(Calendar.DAY_OF_WEEK)
        for (i in 1 until dayNum) {
            dayList.add("")
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1)

        var adapter : GridAdapter

//        var list : ArrayList<Item> = ArrayList<Item>()
//        var it1 : Item = Item()
//        it1.date="1"
//        it1.image = R.drawable.color
//        list.add(it1)
//
//        var it2 : Item = Item()
//        it2.date="1"
//        it2.image = R.drawable.color
//        list.add(it2)
//
//        var it3 : Item = Item()
//        it3.date="1"
//        it3.image = R.drawable.color
//        list.add(it3)

        adapter = GridAdapter(this, dayList)
        datesGridView.adapter = adapter

    }

    private fun setCalendarDate(month: Int) {
        mCal.set(Calendar.MONTH, month - 1)

        for (i in 0 until mCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            dayList.add("" + (i + 1))
        }

    }

    private class GridAdapter (private var activity: Activity, private var list: ArrayList<String>) : BaseAdapter() {

        class GridAdapter (context : Context, list : List<String>){
            var list : List<String> = ArrayList<String>()
            var inflater : LayoutInflater = LayoutInflater.from(context)
            init{
                this.list = list
                this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
        }

        private class ViewHolder(row: View?) {
            var date: TextView? = null
            var image: ImageView? = null

            init {
                this.date = row?.findViewById<TextView>(R.id.dateTextView)
                this.image = row?.findViewById<ImageView>(R.id.pictureImageView)

            }
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val viewHolder: ViewHolder
            if (convertView == null) {
                val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.grid_each_date, null)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            var it = list[position]
            viewHolder.date?.setText(""+ getItem(position))
            return view
        }

        override fun getItem(position: Int): Any {
            return list.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return list.size
        }
    }
}
