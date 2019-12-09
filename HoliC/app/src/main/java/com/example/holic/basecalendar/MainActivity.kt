package com.example.holic.basecalendar

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.holic.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_schedule.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_schedule.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

        //색상변경
        var setColorImage = findViewById<ImageView>(R.id.setColorImage)
        setColorImage.setOnClickListener {
            var items = arrayOf("default", "red", "yellow", "green", "blue", "violet", "pink")
            var weekLinearLayout = findViewById<LinearLayout>(R.id.weekLinearLayout)
            var menuLinearLayout = findViewById<LinearLayout>(R.id.menuLinearLayout)

            var builder = AlertDialog.Builder(this)
            builder.setTitle("테마 변경")
            builder.setIcon(R.drawable.palette)
            builder.setSingleChoiceItems(items, -1,  { _, which->
                if(items[which].equals("default")) {
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#e0e7ee"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#e0e7ee"))
                }
                if(items[which].equals("red")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#D89090"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#D89090"))
                }
                if(items[which].equals("blue")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#B5E3D5"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#B5E3D5"))
                }
                if(items[which].equals("green")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#E0DC96"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#E0DC96"))
                }
                if(items[which].equals("violet")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#CCC6E6"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#CCC6E6"))
                }
                if(items[which].equals("yellow")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#EED39A"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#EED39A"))
                }
                if(items[which].equals("pink")){
                    weekLinearLayout.setBackgroundColor(Color.parseColor("#EFB7B7"))
                    menuLinearLayout.setBackgroundColor(Color.parseColor("#EFB7B7"))
                }
            })

            val AlertDialog = builder.create()
            AlertDialog.show()
        }


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

