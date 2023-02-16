package com.example.todoapp.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.ToggleButton
import com.example.todoapp.R
class SettingFragment : Fragment() {

lateinit var languageSpinner: Spinner
lateinit var modeToggle: ToggleButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languageSpinner= view.findViewById(R.id.LanguageSpinner)
        modeToggle= view.findViewById(R.id.ModeToggle)
        ArrayAdapter.createFromResource(requireActivity(),R.array.Language,R.layout.spinner_item).also {Adapter->
            Adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            languageSpinner.adapter= Adapter

        }
        modeToggle.setOnCheckedChangeListener { compoundButton, b ->
            Toast.makeText(requireContext(),if(b)"night mode is on" else "night mode is off",Toast.LENGTH_LONG).show()
            

        }

        }




}