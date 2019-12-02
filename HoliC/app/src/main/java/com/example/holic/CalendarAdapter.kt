//package com.example.holic
//
//import android.app.Activity
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.ImageView
//import android.widget.TextView
//
//class CalendarAdapter (private var activity: Activity, private var items: ArrayList<Item>) :  BaseAdapter(){
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
//        var it = items[position]
//        viewHolder.date?.text =  it.date
//        viewHolder.image?.setImageResource(it.image!!)
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return items[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return items.size
//    }
//
//}