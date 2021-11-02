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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentHome : Fragment() {
    lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var rv: RecyclerView
    lateinit var adapter: Adapter
    lateinit var items: ArrayList<Notes>

    lateinit var et:EditText
    lateinit var button: Button

 //   lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

//        sharedPreferences = requireActivity().getSharedPreferences(
//            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        items = arrayListOf()
        rv = view.findViewById(R.id.rv)
        adapter = Adapter(this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.readFromDB().observe(viewLifecycleOwner, { items -> adapter.update(items) })

        et = view.findViewById(R.id.et)
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val newNote = et.text.toString()
            if(newNote != "") {
                mainActivityViewModel.insert(Notes("",newNote))
                et.text.clear()
                Toast.makeText(requireContext(), "Note added", Toast.LENGTH_LONG).show()
            }
        }

        mainActivityViewModel.getNotes()
        return view
    }

    override fun onResume() {
        super.onResume()
        // We call the 'getData' function from our ViewModel after a one second delay because Firestore takes some time
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            mainActivityViewModel.getNotes()
        }
    }
}