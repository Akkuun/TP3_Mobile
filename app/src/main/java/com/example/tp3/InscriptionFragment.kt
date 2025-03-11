package com.example.tp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        // Get the button from the submit id
        val btnSoumettre = view.findViewById<Button>(R.id.buttonSubmit)

        // listener
        btnSoumettre.setOnClickListener {
            //when clicked go to the next fragment
            Toast.makeText(context, "Inscription réussie", Toast.LENGTH_SHORT).show()
            //Display the detailsFragment
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main, DetailsFragment()).commit()

        }
    }
}