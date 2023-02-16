package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.todoapp.fragment.AddFragment
import com.example.todoapp.fragment.ListFragment
import com.example.todoapp.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNavigation :BottomNavigationView
    lateinit var fab : FloatingActionButton
    var listFragment = ListFragment()
    private val showDetailsActivity=ShowDetailsActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        fab = findViewById(R.id.FAB)
        fab.setOnClickListener {
            val addBottomSheet = AddFragment()
            addBottomSheet.show(supportFragmentManager,"")
            addBottomSheet.onAddClick= object : AddFragment.OnAddClick{
                override fun onClick() {
                    listFragment.refreshTodos()
                }

            }
        }

        showDetailsActivity.onUpdateClick= object :ShowDetailsActivity.OnUpdateClick{
            override fun onClickUpDate() {
                listFragment.refreshTodos()
                Log.e("onClickUpDate: ","refreshTodos" )
            }

        }
        ShowFragment(listFragment)
        bottomNavigation.setOnItemSelectedListener {TwoItem->

            if (TwoItem.itemId==R.id.ListToDO){
                ShowFragment(listFragment)

            }else if (TwoItem.itemId==R.id.Setting){
                ShowFragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }


    }
fun ShowFragment(fragment :Fragment){
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer,fragment)
        .commit()
}
}