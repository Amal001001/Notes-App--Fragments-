package com.example.notesappfragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.databinding.ItemRowBinding

class Adapter (private var activity: FragmentHome): RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    private var notes = emptyList<Notes>()
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.apply {
            tv.text = note.noteText
            ibUpdate.setOnClickListener {
//                with(activity.sharedPreferences.edit()) {
//                    putString("NoteId", note.id)
//                    putString("NoteText", note.noteText)
//                    apply()
//                }
                // activity.findNavController().navigate(R.id.action_fragmentHome_to_fragmentAdd)

                val bundle = bundleOf("NoteId" to note.id,"NoteText" to note.noteText)
                activity.findNavController().navigate(R.id.action_fragmentHome_to_fragmentAdd, bundle)
            }
            ibDelete.setOnClickListener {
                activity.mainActivityViewModel.delete(note.id)
            }
        }
    }
    override fun getItemCount() = notes.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(notes: List<Notes>){
        this.notes = notes
        notifyDataSetChanged()
    }
}