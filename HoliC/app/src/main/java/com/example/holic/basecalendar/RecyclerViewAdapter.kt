package com.example.holic.basecalendar

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holic.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_schedule.view.*
import kotlinx.android.synthetic.main.item_schedule.*
import java.util.*

class RecyclerViewAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ViewHolderHelper>() {

    val baseCalendar = BaseCalendar()

    //firebase
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("schedule")

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
                databaseReference.child("scheduleName").setValue(add_schedule)
                databaseReference.child("scheduleLocation").setValue(add_location)
                Log.v("add", add_schedule)
                Log.v("add", "스케쥴추가")
                AlertDialog.dismiss()
                //databaseReference.child("schedule").child("scheduleName").push().setValue(add_schedule)
                //databaseReference.child("schedule").child("scheduleLocation").push().setValue(add_location)
            }
            //취소버튼 눌렀을때
            dialogView.button_cancel.setOnClickListener {
                AlertDialog.cancel()
            }
        }
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