package com.example.todoapp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.todoapp.DataBase.MyDataBase
import com.example.todoapp.DataBase.Todo
import com.example.todoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class AddFragment : BottomSheetDialogFragment() {
    lateinit var titleTextInput: TextInputLayout
    lateinit var descriptionTextInput: TextInputLayout
    lateinit var selectDateTV: TextView
    lateinit var addToDoButton: Button
    var onAddClick: OnAddClick? = null
    var selectedDate = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview(view)
        initListener()
    }

    fun initview(view: View) {
        titleTextInput = view.findViewById(R.id.TitleTextInput)
        descriptionTextInput = view.findViewById(R.id.DescriptionTextInput)
        addToDoButton = view.findViewById(R.id.addTodoButton)
        selectDateTV = view.findViewById(R.id.selectedDateTv)
        upDateDate()

    }

    fun initListener() {
        addToDoButton.setOnClickListener {
            selectedDate.clear(Calendar.SECOND)
            selectedDate.clear(Calendar.MILLISECOND)
            selectedDate.clear(Calendar.MINUTE)
            selectedDate.clear(Calendar.HOUR)
            if (!Valid()) {
                return@setOnClickListener
            }
            val todo = Todo(
                title = titleTextInput.editText!!.text.toString(),
                description = descriptionTextInput.editText!!.text.toString(), isDone = false,
                date = selectedDate.time.time,
            )
            MyDataBase.getInstance(requireContext()).getTodeDao().addTodo(todo)
            onAddClick?.onClick()
            dismiss()
        }
        selectDateTV.setOnClickListener {
            val dialog = DatePickerDialog(
                requireContext(),
                { po, year, month, day ->
                    selectedDate.set(year, month, day)
                    upDateDate()
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

    }

    fun Valid(): Boolean {
        var IsValid = true
        if (titleTextInput.editText!!.text.isEmpty()) {
            titleTextInput.error = "please enter title"
            IsValid = false
        } else {
            titleTextInput.error = null
        }
        if (descriptionTextInput.editText!!.text.isEmpty()) {
            descriptionTextInput.error = "please enter description"
            IsValid = false
        } else {
            descriptionTextInput.error = null
        }
        return IsValid
    }

    fun upDateDate() {
        selectDateTV.text =
            "${selectedDate.get(Calendar.DAY_OF_MONTH)}/${selectedDate.get(Calendar.MONTH) + 1}/${
                selectedDate.get(Calendar.YEAR)
            }"
    }

    interface OnAddClick {
        fun onClick()
    }
}