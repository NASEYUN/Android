package com.example.holic.basecalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.holic.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var scheduleRecyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
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

