package com.example.holic.basecalendar

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView
import com.example.holic.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_schedule.view.*
import kotlinx.android.synthetic.main.item_schedule.*
import java.util.*

class RecyclerViewAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ViewHolderHelper>() {

    val baseCalendar = BaseCalendar()
    //val cal = Calendar.getInstance()

    //firebase
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("user")
    val scheduledatabaseRefernce = databaseReference.child("schedule")

    //weather
    var result = WeatherTask().execute().get()
    var spl2 = result.substring(1,result.length-1)
    var spl = spl2.split(", ")
    var c = Calendar.getInstance()

    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolderHelper(view)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {

        if (position % BaseCalendar.DAYS_OF_WEEK == 0) holder.dateTextView.setTextColor(Color.parseColor("#ff1200"))
        else holder.dateTextView.setTextColor(Color.parseColor("#676d6e"))

        if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.dateTextView.alpha = 0.3f
        } else {
            holder.dateTextView.alpha = 1f
        }
        holder.dateTextView.text = baseCalendar.data[position].toString()

        holder.dateTextView.setOnClickListener{
            var dialogView = LayoutInflater.from(holder.dateTextView.context).inflate(R.layout.activity_add_schedule, null)

            var builder = AlertDialog.Builder(holder.dateTextView.context).setView(dialogView).setTitle("일정 등록")
            //show dialog
            val AlertDialog = builder.create()
            AlertDialog.show()

            //확인버튼 눌렀을때
            dialogView.button_confirm.setOnClickListener {
                //값저장
                val add_schedule = dialogView.editText_Calendar_Add.text.toString()
                val add_location = dialogView.editText_Location.text.toString()
                val add_Time = dialogView.timePicker_Time
                add_Time.setIs24HourView(true)
                //입력한 시간과 분 가져오기
                val schedule_Time = add_Time.hour.toString() + "시" + add_Time.minute.toString()+"분"
                Log.v("seyuuun", schedule_Time)
                databaseReference.child("schedule").child("scheduleName").push().setValue(add_schedule)
                databaseReference.child("schedule").child("scheduleLocation").push().setValue(add_location)
                databaseReference.child("schedule").child("scheduleTime").push().setValue(schedule_Time)
                AlertDialog.dismiss()
            }
            //취소버튼 눌렀을때
            dialogView.button_cancel.setOnClickListener {
                AlertDialog.cancel()
            }
        }
        //날씨처리
        if(baseCalendar.nowMonth==(c.get(Calendar.MONTH)+1).toString()) {
            if (baseCalendar.data[position].toString().equals((c.get(Calendar.DATE)).toString()) && baseCalendar.currentMonthMaxDate.toString().equals(
                    (baseCalendar.data.size - baseCalendar.nextMonthHeadOffset).toString()
                )
            )
                holder.imageWeather?.setImageResource(spl[0].toInt())
            if (baseCalendar.data[position].toString().equals((c.get(Calendar.DATE) + 1).toString()) &&
                baseCalendar.currentMonthMaxDate.toString().equals((baseCalendar.data.size - baseCalendar.nextMonthHeadOffset).toString())
            )
                holder.imageWeather?.setImageResource(spl[1].toInt())
            if (baseCalendar.data[position].toString().equals((c.get(Calendar.DATE) + 2).toString()) &&
                baseCalendar.currentMonthMaxDate.toString().equals((baseCalendar.data.size - baseCalendar.nextMonthHeadOffset).toString())
            )
                holder.imageWeather?.setImageResource(spl[2].toInt())
            if (baseCalendar.data[position].toString().equals((c.get(Calendar.DATE) + 3).toString()) &&
                baseCalendar.currentMonthMaxDate.toString().equals((baseCalendar.data.size - baseCalendar.nextMonthHeadOffset).toString())
            )
                holder.imageWeather?.setImageResource(spl[3].toInt())
            if (baseCalendar.data[position].toString().equals((c.get(Calendar.DATE) + 4).toString()) &&
                baseCalendar.currentMonthMaxDate.toString().equals((baseCalendar.data.size - baseCalendar.nextMonthHeadOffset).toString())
            )
                holder.imageWeather?.setImageResource(spl[4].toInt())
        }
        if (position>baseCalendar.currentMonthMaxDate)
            holder.imageWeather?.setImageResource(0)

    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }
}