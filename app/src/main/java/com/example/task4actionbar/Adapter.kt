package com.example.task4actionbar


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(var list: ArrayList<Model>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name=view.findViewById<TextView>(R.id.title2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val out=list[position]
        holder.name.text=out.name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}