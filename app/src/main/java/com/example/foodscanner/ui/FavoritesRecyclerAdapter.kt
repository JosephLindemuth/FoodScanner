package com.example.foodscanner.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.R
import java.util.*

class FavoritesRecyclerAdapter() : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesHolder>()
{
    private val files: Vector<FavoritesItem> = Vector()

    constructor(files: Array<String>) : this()
    {
        for (upc in files)
        {
            this.files.add(FavoritesItem(upc))
        }
    }

    fun addUnique(newFile: FavoritesItem)
    {
        if (contains(newFile) == false)
        {
            addFile(newFile)
        }
    }

    fun addFile(newFile: FavoritesItem)
    {
        files.add(newFile)
    }

    fun removeFile(newFile: FavoritesItem)
    {
        files.remove(newFile)
    }

    fun contains(thisFile: FavoritesItem) : Boolean
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder
    {
        return FavoritesHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false))
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int)
    {
        val current: FavoritesItem = files[position]

        holder.upc.text = current.upc
    }

    class FavoritesHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val upc: TextView = itemView.findViewById<TextView>(R.id.upc)
    }
}