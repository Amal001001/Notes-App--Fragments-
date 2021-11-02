package com.example.notesappfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentAdd : Fragment() {
    lateinit var mainActivityViewModel: MainActivityViewModel
 //   lateinit var sharedPreferences : SharedPreferences
    lateinit var updatedText :EditText
    var noteId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

//        sharedPreferences = requireActivity().getSharedPreferences(
//            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

//        noteId = sharedPreferences.getString("NoteId", "").toString()
//        noteText = sharedPreferences.getString("NoteText", "").toString()
//        updatedText.setText(noteText)

        updatedText = view.findViewById(R.id.etFUpdate)

        noteId = arguments?.getString("NoteId")!!
        updatedText.setText(arguments?.getString("NoteText"))

        var btnUpdate = view.findViewById<Button>(R.id.btnFUpdate).setOnClickListener { updateF() }
        return view

    }

    fun updateF(){
        mainActivityViewModel.update(noteId, updatedText.text.toString())
        findNavController().navigate(R.id.action_fragmentAdd_to_fragmentHome)
    }

}