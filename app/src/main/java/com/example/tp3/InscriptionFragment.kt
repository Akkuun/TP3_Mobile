package com.example.tp3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.log

class InscriptionFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.inscription_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the button from the submit id
        val btnSoumettre = view.findViewById<Button>(R.id.buttonSubmit)



        // listener
        btnSoumettre.setOnClickListener {
            // get user's input
            val login = view.findViewById<EditText>(R.id.editTextLogin).toString();
            val nom = view.findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val prenom = view.findViewById<EditText>(R.id.editTextLastName).text.toString()
            val telephone = view.findViewById<EditText>(R.id.editTextPhoneNumber).text.toString()
            val email = view.findViewById<EditText>(R.id.editTextEmail).text.toString()
            val dateNaissance = view.findViewById<EditText>(R.id.datePickerBirthDate).text.toString()

            // Get the checkboxes
            val checkBoxList = listOf(
                view.findViewById<CheckBox>(R.id.checkBoxCinema),
                view.findViewById<CheckBox>(R.id.checkBoxMusic),
                view.findViewById<CheckBox>(R.id.checkBoxSport),
                view.findViewById<CheckBox>(R.id.checkBoxReading)
            )


            //concateneant all the checked checkboxes into a string
            val checkBoxesString = checkBoxList.filter { it.isChecked }.joinToString(", ") { it.text.toString() }



            // Create a bundle to pass the data to the next fragment
            val bundle = Bundle()
            bundle.putString("nom", nom)
            bundle.putString("prenom", prenom)
            bundle.putString("date_naissance", dateNaissance)
            bundle.putString("telephone", telephone)
            bundle.putString("email", email)
            bundle.putString("user", login)
            bundle.putString(checkBoxesString, "checkBoxesString")

           //log
            Log.d("InscriptionFragment", "Nom : $nom")

            // We create a instance of the fragment to pass it to the fragment manager
            val recapFragment = DetailsFragment()
            //asociation of the data
            recapFragment.arguments = bundle

            // Change actual fragment to the next fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, recapFragment) // get the fragment container from the main activity
                .addToBackStack(null)
                .commit()
        }
    }
}