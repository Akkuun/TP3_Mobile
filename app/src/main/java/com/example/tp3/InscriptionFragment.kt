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


            // Get the checkboxes
            val checkBoxList = listOf(
                view.findViewById<CheckBox>(R.id.checkBoxCinema),
                view.findViewById<CheckBox>(R.id.checkBoxMusic),
                view.findViewById<CheckBox>(R.id.checkBoxSport),
                view.findViewById<CheckBox>(R.id.checkBoxReading)
            )
            // get user's input
            val login = view.findViewById<EditText>(R.id.editTextLogin).toString();
            val nom = view.findViewById<EditText>(R.id.editTextFirstName)?.text.toString()
            val prenom = view.findViewById<EditText>(R.id.editTextLastName)?.text.toString()
            val telephone = view.findViewById<EditText>(R.id.editTextPhoneNumber)?.text.toString()
            val email = view.findViewById<EditText>(R.id.editTextEmail)?.text.toString()
            val dateNaissance =
                view.findViewById<EditText>(R.id.datePickerBirthDate)?.text.toString()
            val password = view.findViewById<EditText>(R.id.editTextPassword)?.text.toString()

            //concateneant all the checked checkboxes into a string
            val checkBoxesString =
                checkBoxList.filter { it.isChecked }.joinToString(", ") { it.text.toString() }


            // Create a bundle to pass the data to the next fragment
            val bundle = Bundle()
            bundle.putString("login", login)
            bundle.putString("password", password)
            bundle.putString("nom", nom)
            bundle.putString("prenom", prenom)
            bundle.putString("date_naissance", dateNaissance)
            bundle.putString("telephone", telephone)
            bundle.putString("email", email)
            bundle.putString("user", login)
            bundle.putString("hobbies", checkBoxesString)


            // We create a instance of the fragment to pass it to the fragment manager
            val recapFragment = DetailsFragment()
            //asociation of the data
            recapFragment.arguments = bundle


            // if data correcly filled we pass to the next fragment
            if (this.checkAllData()) {
                // Change actual fragment to the next fragment
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        recapFragment
                    ) // get the fragment container from the main activity
                    .addToBackStack(null)
                    .commit()
            }

        }

    }

    //function that check all the data filled by the user are correct
    private fun checkAllData(): Boolean {
        var passwordOk = false;
        var loginOk = false;
        var nomOk = false;
        var prenomOk = false;
        var telephoneOk = false;
        var emailOk = false;
        var dateNaissanceOk = false;
        var hobbiesOk = false;

        val password = view?.findViewById<EditText>(R.id.editTextPassword)?.text.toString()
        val login = view?.findViewById<EditText>(R.id.editTextLogin)?.text.toString()
        //if password is less than 6 characters, don't have a digit, don't have a caps character we put a red background
        if (password.length < 6 || !password.any { it.isDigit() } || !password.any { it.isUpperCase() }) {
            view?.findViewById<EditText>(R.id.editTextPassword)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(
                context,
                "MDP incorrect, doit avoir > 6 lettres, un digit et une majuscule",
                Toast.LENGTH_LONG
            ).show()
        } else {
            passwordOk = true;
            view?.findViewById<EditText>(R.id.editTextPassword)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }

        //if login not begining by a letter or have a space we put a red background and more than 11 characters, we put a red background
        if (login.isEmpty() || login.length > 11 || !login[0].isLetter() || login.contains(" ")) {
            view?.findViewById<EditText>(R.id.editTextLogin)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(
                context,
                "doit avoir <11 lettres,pas d'espace, pas exister dans la BD",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            loginOk = true;
            view?.findViewById<EditText>(R.id.editTextLogin)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }

        //if nom is empty we put a red background
        if (view?.findViewById<EditText>(R.id.editTextFirstName)?.text.toString().isEmpty()) {
            view?.findViewById<EditText>(R.id.editTextFirstName)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(context, "information manquante", Toast.LENGTH_SHORT).show()
        } else {
            nomOk = true;
            view?.findViewById<EditText>(R.id.editTextFirstName)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }

        // if prenom is empty we put a red background
        if (view?.findViewById<EditText>(R.id.editTextLastName)?.text.toString().isEmpty()) {
            view?.findViewById<EditText>(R.id.editTextLastName)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(context, "information manquante", Toast.LENGTH_SHORT).show()
        } else {
            prenomOk = true;
            view?.findViewById<EditText>(R.id.editTextLastName)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }
        //if telephone is empty we put a red background
        if (view?.findViewById<EditText>(R.id.editTextPhoneNumber)?.text.toString().isEmpty()) {
            view?.findViewById<EditText>(R.id.editTextPhoneNumber)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(context, "information manquante", Toast.LENGTH_SHORT).show()
        } else {
            telephoneOk = true;
            view?.findViewById<EditText>(R.id.editTextPhoneNumber)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }
        //if email is empty we put a red background
        if (view?.findViewById<EditText>(R.id.editTextEmail)?.text.toString().isEmpty()) {
            view?.findViewById<EditText>(R.id.editTextEmail)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(context, "information manquante", Toast.LENGTH_SHORT).show()
        } else {
            emailOk = true;
            view?.findViewById<EditText>(R.id.editTextEmail)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }

        //if dateNaissance is empty we put a red background
        if (view?.findViewById<EditText>(R.id.datePickerBirthDate)?.text.toString().isEmpty()) {
            view?.findViewById<EditText>(R.id.datePickerBirthDate)
                ?.setBackgroundColor(resources.getColor(R.color.red))
            Toast.makeText(context, "information manquante", Toast.LENGTH_SHORT).show()
        } else {
            dateNaissanceOk = true;
            view?.findViewById<EditText>(R.id.datePickerBirthDate)
                ?.setBackgroundColor(resources.getColor(R.color.white))
        }

        //if no checkbox is checked we put a toast
        if (view?.findViewById<CheckBox>(R.id.checkBoxCinema)?.isChecked == false &&
            view?.findViewById<CheckBox>(R.id.checkBoxMusic)?.isChecked == false &&
            view?.findViewById<CheckBox>(R.id.checkBoxSport)?.isChecked == false &&
            view?.findViewById<CheckBox>(R.id.checkBoxReading)?.isChecked == false
        ) {
            Toast.makeText(context, "Ajoute au moins 1 centre d'interêt", Toast.LENGTH_SHORT).show()
        } else {
            hobbiesOk = true;
        }



        return passwordOk && loginOk && nomOk && prenomOk && telephoneOk && emailOk && dateNaissanceOk && hobbiesOk

    }


}