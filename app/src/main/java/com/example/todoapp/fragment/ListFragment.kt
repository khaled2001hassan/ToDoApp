package com.example.todoapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapter_setting
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.Todo
import com.example.todoapp.R
import com.example.todoapp.ShowDetailsActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar


class ListFragment : Fragment() {
    lateinit var calenderView: MaterialCalendarView
    lateinit var recycleView: RecyclerView
    var adapter = Adapter_setting(listOf())
    var selectedDay : Calendar = Calendar.getInstance()
    lateinit var noTaskImage :ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initListener()
        refreshTodos()
    }

    fun initView(view: View) {
        calenderView = view.findViewById(R.id.MaterialCalenderView)
        recycleView = view.findViewById(R.id.ListRecycleView)
        noTaskImage = view.findViewById(R.id.NoTaskImage)


    }

    fun initListener() {
        calenderView.selectedDate = CalendarDay.today()
        adapter.onImageClick = object : Adapter_setting.OnImgeClick {
            override fun onClick(todoClick: Todo) {
                MyDataBase.getInstance(requireContext()).getTodeDao().deleteTodo(todoClick)
                refreshTodos()
            }

        }
        adapter.onTitleClick = object : Adapter_setting.OnImgeClick {
            override fun onClick(todoClick: Todo) {
                val intent = Intent(requireActivity(), ShowDetailsActivity::class.java)
                val date = todoClick.date.toString()
                val stringId = todoClick.id.toString()
                Log.e( "id name", "${todoClick.id}" )
                intent.putExtra("title", todoClick.title)
                intent.putExtra("description", todoClick.description)
                intent.putExtra("date", date)
                intent.putExtra("id",stringId)
                startActivity(intent)


            }

        }
        calenderView.setOnDateChangedListener(object : OnDateSelectedListener{
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                selectedDay.set( date.year,date.month-1, date.day)
                refreshTodos()
            }

        })
    }

    fun refreshTodos() {
        selectedDay.clear(Calendar.SECOND)
        selectedDay.clear(Calendar.MILLISECOND)
        selectedDay.clear(Calendar.MINUTE)
        selectedDay.clear(Calendar.HOUR)
        var todos = MyDataBase.getInstance(requireContext()).getTodeDao().getTodoByDate(
            selectedDay.time.time
        )
        if (todos.isEmpty()){
            noTaskImage.visibility = View.VISIBLE
        }else {
            noTaskImage.visibility = View.INVISIBLE
        }
        adapter.changeTodo(todos)
        recycleView.adapter = adapter
    }
}