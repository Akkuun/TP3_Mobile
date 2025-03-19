package com.example.tp3

import HomePageFragment
import PlaningDetailFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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

            Toast.makeText(requireContext(), "Slot1: $slot1, Slot2: $slot2, Slot3: $slot3, Slot4: $slot4", Toast.LENGTH_SHORT).show()
            //login session actual
            val sharedPreferences = requireActivity().getSharedPreferences("session", android.content.Context.MODE_PRIVATE)
            val userLogin = sharedPreferences.getString("user_login", null)
            Toast.makeText(requireContext(), "User login: $userLogin", Toast.LENGTH_SHORT).show()

            val planing = Planing(slot1, slot2, slot3, slot4)
            val databaseUserHelper = DatabaseUserHelper(requireContext())
            if (databaseUserHelper.insertPlaning(planing)) {
                val fragment = PlaningDetailFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

    }

}