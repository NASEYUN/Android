package com.example.holic

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    var imgList : ArrayList<Int> = ArrayList<Int>()

    var mCal : Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val instance = Calendar.getInstance()
//        val year = instance.get(Calendar.YEAR).toString()
//        val month = (instance.get(Calendar.MONTH)+1).toString()

        var currentTextView = findViewById<TextView>(R.id.currentTextView)

        var now = System.currentTimeMillis()
        var date : Date = Date(now)
        var curYearFormat : SimpleDateFormat = SimpleDateFormat("yyyy",Locale.KOREA)
        var curMonthFormat : SimpleDateFormat = SimpleDateFormat("MM",Locale.KOREA)
        var curDateFormat : SimpleDateFormat = SimpleDateFormat("dd",Locale.KOREA)

        initCalendar(date, currentTextView, curYearFormat, curMonthFormat)

        /*
        var now = System.currentTimeMillis()
        var date : Date = Date(now)
        var curYearFormat : SimpleDateFormat = SimpleDateFormat("yyyy",Locale.KOREA)
        var curMonthFormat : SimpleDateFormat = SimpleDateFormat("MM",Locale.KOREA)
        var curDateFormat : SimpleDateFormat = SimpleDateFormat("dd",Locale.KOREA)

        currentTextView.setText(curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월")

        // 이번달 1일이 무슨 요일인지 판단. mCal.set(Year, Month, Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)),Integer.parseInt(curMonthFormat.format(date)) - 1, 1)

        // 1일의 요일을 매칭시키기 위해 공백을 추가
        var dayNum = mCal.get(Calendar.DAY_OF_WEEK)
        for (i in 1 until dayNum) {
            dayList.add("")
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1)
        */

        var adapter : GridAdapter
        adapter = GridAdapter(this, dayList)
        datesGridView.adapter = adapter

        var count : Int = 0

        // 이전달
        var previous = findViewById<ImageView>(R.id.previousMonthButton)
        previous.setOnClickListener {
            count-=1 //버튼 누를때마다 카운트 감소
            var year = Integer.parseInt(curYearFormat.format(date))
            var month = Integer.parseInt(curMonthFormat.format(date))-1
            var setMonth = month+count
            var setYear = year

            dayList.clear()
            //mCal = Calendar.getInstance()
            // 이번달 1일이 무슨 요일인지 판단. mCal.set(Year, Month, Day)

            //currentTextView.setText(curYearFormat.format(date) + "년 " + (setMonth+1).toString() + "월")
            //1월인경우
//            if(setMonth != 1) {
//                currentTextView.setText(year.toString() + "년 " + (setMonth+1).toString() + "월")
//            } else if(setMonth == 1) { //1월인경우
//                setMonth==12
//                year-=1
//                currentTextView.setText(year.toString() + "년 " + setMonth.toString() + "월")
//
//            }
            if(setMonth <= 0) {
                if(setMonth == 0) setMonth = 12
                else {
                    setMonth += 12
                    setMonth %= 12
                }
                setYear -= 1
            }
            if(setMonth >= 13)

            //currentTextView.setText(curYearFormat.format(date) + "년 " + (setMonth+1).toString() + "월")
            //Log.d("log", curYearFormat.format(date) + "년 " + (setMonth+1).toString() + "월")
            currentTextView.setText(setYear.toString() + "년 " + setMonth.toString() + "월")
            mCal.set(setYear, setMonth, 1)
            //mCal.set(year,setMonth, 1)

            // 1일의 요일을 매칭시키기 위해 공백을 추가
            var dayNum = mCal.get(Calendar.DAY_OF_WEEK)
            for (i in 1 until dayNum) {
                dayList.add("")
            }
            setCalendarDate(mCal.get(Calendar.MONTH) + 1)

            adapter.notifyDataSetChanged()
        }

        // 다음달
        var next = findViewById<ImageView>(R.id.nextMonthButton)
        next.setOnClickListener {
            count+=1 //버튼 누를때마다 카운트 감소
            var year = Integer.parseInt(curYearFormat.format(date))
            var month = Integer.parseInt(curMonthFormat.format(date))-1
            var setMonth = month+count
            var setYear = year

            dayList.clear()
            //mCal = Calendar.getInstance()

            // 이번달 1일이 무슨 요일인지 판단. mCal.set(Year, Month, Day)

            //currentTextView.setText(curYearFormat.format(date) + "년 " + (setMonth+1).toString() + "월")
            //12월인경우
//            if(setMonth != 12) {
//                currentTextView.setText(year.toString() + "년 " + (setMonth+1).toString() + "월")
//            } else if(setMonth == 12) { //1월인경우
//                setMonth==1
//                year+=1
//                currentTextView.setText(year.toString() + "년 " + setMonth.toString() + "월")
//
//            }
            if(setMonth >= 13) {
                setMonth %= 12
                setYear += 1
            }
            //currentTextView.setText(curYearFormat.format(date) + "년 " + (setMonth+1).toString() + "월")
            currentTextView.setText(setYear.toString() + "년 " + setMonth.toString() + "월")
            mCal.set(setYear, setMonth, 1)

            //mCal.set(year,setMonth, 1)
            //val saveCurrentTextView =
            //currentTextView.setText(curYearFormat.format(date) + "년 " + (month+1).toString() + "월")

//            if(month != 12) {
//                currentTextView.setText(curYearFormat.format(date) + "년 " + (month+1).toString() + "월")
//            } else if(month == 12) {
//                currentTextView.setText((curYearFormat.format(date).toInt()+1).toString() + "년 " + (month-11).toString() + "월")
//            }

            // 1일의 요일을 매칭시키기 위해 공백을 추가
            var dayNum = mCal.get(Calendar.DAY_OF_WEEK)
            for (i in 1 until dayNum) {
                dayList.add("")
            }
            setCalendarDate(mCal.get(Calendar.MONTH) + 1)

            adapter.notifyDataSetChanged()
        }
    }

    private fun initCalendar(date: Date, currentTextView: TextView, curYearFormat: SimpleDateFormat, curMonthFormat: SimpleDateFormat) {
        currentTextView.setText(curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월")

        // 이번달 1일이 무슨 요일인지 판단. mCal.set(Year, Month, Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)),Integer.parseInt(curMonthFormat.format(date)) - 1, 1)

        // 1일의 요일을 매칭시키기 위해 공백을 추가
        var dayNum = mCal.get(Calendar.DAY_OF_WEEK)
        for (i in 1 until dayNum) {
            dayList.add("")
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1)
    }

    // 해당 월에 표시할 일(date)을 구함
    private fun setCalendarDate(month: Int) {
        mCal.set(Calendar.MONTH, month - 1)

        for (i in 0 until mCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            dayList.add("" + (i + 1))
        }
    }

    // 그리드뷰 어댑터
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
