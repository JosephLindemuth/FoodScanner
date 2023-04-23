package com.example.foodscanner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import org.w3c.dom.Text
import java.util.*

class HistoryRecyclerAdapter() : RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryHolder>()
{
    private val files: Vector<HistoryItem> = Vector()

    constructor(files: Array<String>) : this()
    {

        for (upc in files)
        {
            this.files.add(HistoryItem(upc))
        }
    }

    fun addUnique(newFile: HistoryItem)
    {
        if (contains(newFile) == false)
        {
            addFile(newFile)
        }
    }

    fun addFile(newFile: HistoryItem)
    {
        files.add(newFile)
    }

    fun removeFile(newFile: HistoryItem)
    {
        files.remove(newFile)
    }

    fun contains(thisFile: HistoryItem) : Boolean
    {
        for (file in files)
        {
            if (file.upc == thisFile.upc)
            {
                return true
            }
        }

        return false
    }

    override fun getItemCount(): Int
    {
        return files.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder
    {
        return HistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int)
    {

        val current: HistoryItem = files[position]

        holder.upc.text = current.upc
    }


    class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val upc: TextView = itemView.findViewById<TextView>(R.id.upc)


    }
}