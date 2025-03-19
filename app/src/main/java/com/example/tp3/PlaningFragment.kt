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

class PlaningFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.planing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseplaningHelper = DatabasePlaningHelper(requireContext())

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

            //get the data from the slots
            val slot1 = view.findViewById<TextView>(R.id.editTextSlot1).text.toString()
            val slot2 = view.findViewById<TextView>(R.id.editTextSlot2).text.toString()
            val slot3 = view.findViewById<TextView>(R.id.editTextSlot3).text.toString()
            val slot4 = view.findViewById<TextView>(R.id.editTextSlot4).text.toString()

            //login session actual
            val sharedPreferences = requireActivity().getSharedPreferences(
                "session",
                android.content.Context.MODE_PRIVATE
            )
            val userLogin = sharedPreferences.getString("user_login", null)

            // creation of the planing object to insert in the database after
            val planing = Planing(userLogin.toString(), slot1, slot2, slot3, slot4)
            //bool val to check if the planing is correctly inserted in the db
            val inserted = databaseplaningHelper.insertPlaning(planing)
            if (inserted) {
                Toast.makeText(requireContext(), "Planning enregistré", Toast.LENGTH_SHORT).show()
                val fragment = PlaningDetailFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Erreur lors de l'enregistrement du planning",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

}