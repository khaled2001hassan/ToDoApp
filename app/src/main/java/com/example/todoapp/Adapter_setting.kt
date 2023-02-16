package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DataBase.Todo


class Adapter_setting( var itemTodo:List<Todo> ):RecyclerView.Adapter<Adapter_setting.ViewHolder>() {
     var onImageClick:OnImgeClick?=null

    var onTitleClick:OnImgeClick?=null
    class ViewHolder( val itemView: View):RecyclerView.ViewHolder(itemView){
        val   title= itemView.findViewById<TextView>(R.id.TaskName)
        val   dascription= itemView.findViewById<TextView>(R.id.TaskDescription)
        val  imageClick= itemView.findViewById<ImageView>(R.id.ClickDone)

    }
      fun changeTodo(item:List<Todo>){
    itemTodo = item
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val intint = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return ViewHolder(intint)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemTodo.get(position)
        holder.title.text = item.title
        holder.dascription.text=item.description
        holder.imageClick.setOnClickListener {
            onImageClick?.onClick(item)
        }
        holder.itemView.setOnClickListener {
            onTitleClick?.onClick(item)
        }

    }

    override fun getItemCount(): Int {
        return itemTodo.size
    }
    interface OnImgeClick{
        fun onClick(todoClick:Todo)
    }

}