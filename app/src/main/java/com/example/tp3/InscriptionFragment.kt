package com.example.tp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

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

        val databaseUserHelper = DatabaseUserHelper(requireContext())

        val btnHome = view.findViewById<Button>(R.id.buttongoBack)
        btnHome.setOnClickListener {
            //go back to the home page
            parentFragmentManager.popBackStack()
        }


        val btnSoumettre = view.findViewById<Button>(R.id.buttonSubmit)
        btnSoumettre.setOnClickListener {
            val login = view.findViewById<EditText>(R.id.editTextLogin)?.text.toString()
            val password = view.findViewById<EditText>(R.id.editTextPassword)?.text.toString()
            val nom = view.findViewById<EditText>(R.id.editTextFirstName)?.text.toString()
            val prenom = view.findViewById<EditText>(R.id.editTextLastName)?.text.toString()
            val telephone = view.findViewById<EditText>(R.id.editTextPhoneNumber)?.text.toString()
            val email = view.findViewById<EditText>(R.id.editTextEmail)?.text.toString()
            val dateNaissance = view.findViewById<EditText>(R.id.datePickerBirthDate)?.text.toString()

            val checkBoxList = listOf(
                view.findViewById<CheckBox>(R.id.checkBoxCinema),
                view.findViewById<CheckBox>(R.id.checkBoxMusic),
                view.findViewById<CheckBox>(R.id.checkBoxSport),
                view.findViewById<CheckBox>(R.id.checkBoxReading)
            )
            val hobbies = checkBoxList.filter { it.isChecked }.joinToString(", ") { it.text.toString() }

            if (databaseUserHelper.isLoginExists(login)) {
                Toast.makeText(requireContext(), "Login déjà existant !", Toast.LENGTH_SHORT).show()
            } else if(!checkAllData()){
                Toast.makeText(requireContext(), "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show()
            }


                else {
                val newUser = User(login, password, nom, prenom, telephone, email, dateNaissance, hobbies)
                if (databaseUserHelper.insertUser(newUser)) {
                    Toast.makeText(requireContext(), "Inscription réussie !", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DetailsFragment().apply {
                            arguments = Bundle().apply {
                                putString("login", login)
                                putString("password", password)
                                putString("nom", nom)
                                putString("prenom", prenom)
                                putString("date_naissance", dateNaissance)
                                putString("telephone", telephone)
                                putString("email", email)
                                putString("hobbies", hobbies)
                            }
                        })
                        .addToBackStack(null)
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show()
                }
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