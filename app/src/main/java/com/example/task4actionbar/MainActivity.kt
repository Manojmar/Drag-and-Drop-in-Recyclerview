package com.example.task4actionbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var ed: EditText
    private lateinit var bt: Button
    private lateinit var rec: RecyclerView
    private lateinit var myadapater: Adapter
    private lateinit var modelist: ArrayList<Model>
    private lateinit var modelist1: ArrayList<Model>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modelist = ArrayList()
        modelist1 = ArrayList()
        ed = findViewById(R.id.edit)
        bt = findViewById(R.id.button)
        rec = findViewById(R.id.rey)

             myadapater = Adapter(modelist)
            rec.adapter = myadapater
            rec.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        bt.setOnClickListener {
            val title: String = ed.text.toString()
            ed.setText("")
            modelist.add(0, Model("Name: $title"))

            val itemTouchHelper= ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(rec)
            myadapater.notifyDataSetChanged()
        }
        }
    private var simpleCallback=object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,0){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition=viewHolder.adapterPosition
            val toPosition=target.adapterPosition

            /* Drag and drop the card*/

//            val item=modelist.removeAt(fromPosition)
//            modelist.add(toPosition,item)
            Collections.swap(modelist,fromPosition,toPosition)
            myadapater.notifyItemMoved(fromPosition, toPosition)
           return true
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item=menu!!.findItem(R.id.savebtn)
        val searchView=item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(p0: String?): Boolean {
                   modelist1.clear()
                   val search= p0!!.lowercase(Locale.getDefault())
                if (search.isNotEmpty()){
                    modelist.forEach{
                        if (it.name.lowercase(Locale.getDefault()).contains(search)){
                            modelist1.add(it)
                        }
                    }
                         myadapater.notifyDataSetChanged()
                }

               else{
                   modelist1.clear()
                   modelist1.addAll(modelist)
                   myadapater.notifyDataSetChanged()
               }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
    }

