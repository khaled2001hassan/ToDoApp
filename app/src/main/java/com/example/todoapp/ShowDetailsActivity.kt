package com.example.todoapp

import android.app.DatePickerDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.Todo
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class ShowDetailsActivity : AppCompatActivity() {
    lateinit var todoTitleView:TextInputLayout
    lateinit var todoDescriptionView:TextInputLayout
   lateinit var dateView:TextView
    lateinit var UpDateTodoButton:Button
    lateinit var BackArrow:ImageView
    var onUpdateClick :OnUpdateClick?=null
    var selectedDay :Calendar =Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        initView()
        val todoTiTle =intent.getStringExtra("title")
        val todoDescription =intent.getStringExtra("description")
        val Date =intent.getStringExtra("date")
        val stringId =intent.getStringExtra("id")
        val idThis = stringId!!.toInt()

        todoTitleView.editText!!.setText(todoTiTle)
        refreshDateView()
        todoDescriptionView.editText!!.setText(todoDescription)
        initListener(idThis)



    }
   fun initView(){
       todoDescriptionView =findViewById(R.id.DescriptionTextInputView)
        todoTitleView =findViewById(R.id.TitleTextInputView)
        dateView=findViewById(R.id.selectedDateTvView)
        UpDateTodoButton=findViewById(R.id.UpDateTodoButton)
       BackArrow=findViewById(R.id.BackArrow)
    }
    fun initListener(idThis:Int){
        dateView.setOnClickListener {
            val dialog = DatePickerDialog(
                 this, {p0,year,month,day ->
                    selectedDay.set(year,month,day)
                    refreshDateView()
                },selectedDay.get(Calendar.YEAR),selectedDay.get(Calendar.MONTH),selectedDay.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

        UpDateTodoButton.setOnClickListener {
            if (!isValid())return@setOnClickListener
            selectedDay.clear(Calendar.SECOND)
            selectedDay.clear(Calendar.MILLISECOND)
            selectedDay.clear(Calendar.MINUTE)
            selectedDay.clear(Calendar.HOUR)
            val todo = Todo(
                title = todoTitleView.editText!!.text.toString(),
                description = todoDescriptionView.editText!!.text.toString(), isDone = false,
                date = selectedDay.time.time,id=idThis
            )
            MyDataBase.getInstance(this).getTodeDao().upDateTodo(todo)
            onUpdateClick?.onClickUpDate()
            finish()
        }
        BackArrow.setOnClickListener {
            finish()
        }

    }

    fun refreshDateView(){
        dateView.setText("${selectedDay.get(Calendar.YEAR)}/${selectedDay.get(Calendar.MONTH)}/${selectedDay.get(Calendar.DAY_OF_MONTH)}")
    }
    fun isValid():Boolean{
        var isTrue =true
        if (todoDescriptionView.editText!!.text.isEmpty()){
            todoDescriptionView.error="please enter description"
            isTrue=false
        }else todoDescriptionView.error=null
        if (todoTitleView.editText!!.text.isEmpty()){
            todoTitleView.error="please enter description"
            isTrue=false
        }else todoTitleView.error=null
        return isTrue
    }
    interface OnUpdateClick{
      fun  onClickUpDate()
    }
}