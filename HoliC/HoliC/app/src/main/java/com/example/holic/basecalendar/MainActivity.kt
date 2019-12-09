package com.example.holic.basecalendar

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.holic.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_schedule.view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var scheduleRecyclerViewAdapter: RecyclerViewAdapter

    //firebase
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("schedule")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        val calendar = findViewById<EditText>(R.id.editText_Calendar_Add)
        val location = findViewById<EditText>(R.id.editText_Location)
        val time = findViewById<Spinner>(R.id.spinner_Time)

        /*val calendar_recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSchedule)
        calendar_recyclerView.setOnClickListener {
            var dialogView = LayoutInflater.from(this).inflate(R.layout.activity_add_schedule, null)

            var builder = AlertDialog.Builder(this).setView(dialogView).setTitle("일정 등록")
            //show dialog
            val AlertDialog = builder.create()
            AlertDialog.show()

            //확인버튼 눌렀을때
            builder.set {
                //값저장
                val add_schedule = calendar.text.toString()
                val add_location = location.text.toString()
                databaseReference.child("schedule").child("scheduleName").setValue(add_schedule)
                databaseReference.child("schedule").child("scheduleLocation").setValue(add_location)
            }
            //취소버튼 눌렀을때
            dialogView.button_cancel.setOnClickListener {
                AlertDialog.cancel()
            }
        }*/
    }

    fun initView() {

        scheduleRecyclerViewAdapter = RecyclerViewAdapter(this)

        recyclerViewSchedule.layoutManager = GridLayoutManager(this, BaseCalendar.DAYS_OF_WEEK)
        recyclerViewSchedule.adapter = scheduleRecyclerViewAdapter
        recyclerViewSchedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recyclerViewSchedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        previousMonthButton.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToPrevMonth()
        }

        nextMonthButton.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToNextMonth()
        }
    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy MM", Locale.KOREAN)
        textViewCurrentMonth.text = sdf.format(calendar.time)
    }
}

