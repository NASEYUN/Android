//package com.example.holic
//
//import android.app.Activity
//import android.app.Application
//import android.content.Context
//import android.text.Layout
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.content.Context.LAYOUT_INFLATER_SERVICE
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.core.content.ContextCompat.getSystemService
//
//
//
//class GridAdapter (private var activity: Activity, private var list: ArrayList<String>) : BaseAdapter() {
//
//    class GridAdapter (context : Context, list : List<String>){
//        var list : List<String> = ArrayList<String>()
//        var inflater : LayoutInflater = LayoutInflater.from(context)
//        init{
//            this.list = list
//            this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        }
//    }
//
//    private class ViewHolder(row: View?) {
//        var date: TextView? = null
//        var image: ImageView? = null
//
//        init {
//            this.date = row?.findViewById<TextView>(R.id.dateTextView)
//            this.image = row?.findViewById<ImageView>(R.id.pictureImageView)
//
//        }
//    }
//
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View
//        val viewHolder: ViewHolder
//        if (convertView == null) {
//            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            view = inflater.inflate(R.layout.grid_each_date, null)
//            viewHolder = ViewHolder(view)
//            view.tag = viewHolder
//        } else {
//            view = convertView
//            viewHolder = view.tag as ViewHolder
//        }
//
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return list.get(position)
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//}