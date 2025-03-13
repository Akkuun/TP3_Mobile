package com.example.tp3

import HomePageFragment
import PlaningDetailFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class PlaningFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.planing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goBackButton = view.findViewById<TextView>(R.id.buttonHome)
        val saveButton = view.findViewById<TextView>(R.id.buttonSave)

        goBackButton.setOnClickListener {
            val fragment = HomePageFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        saveButton.setOnClickListener {
            val slot1 = view.findViewById<TextView>(R.id.editTextSlot1).text.toString()
            val slot2 = view.findViewById<TextView>(R.id.editTextSlot2).text.toString()
            val slot3 = view.findViewById<TextView>(R.id.editTextSlot3).text.toString()
            val slot4 = view.findViewById<TextView>(R.id.editTextSlot4).text.toString()

            val planing = Planing(slot1, slot2, slot3, slot4)
            val databaseHelper = DatabaseHelper(requireContext())
            if (databaseHelper.insertPlaning(planing)) {
                val fragment = PlaningDetailFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

    }

}