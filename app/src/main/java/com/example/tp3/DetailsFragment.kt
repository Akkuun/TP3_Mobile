package com.example.tp3

import HomePageFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonGoBack = view.findViewById<TextView>(R.id.btn_valider)

        // bundle data
        val nom = arguments?.getString("nom") ?: "Non renseigné"
        val prenom = arguments?.getString("prenom") ?: "Non renseigné"
        val dateNaissance = arguments?.getString("date_naissance") ?: "Non renseigné"
        val telephone = arguments?.getString("telephone") ?: "Non renseigné"
        val email = arguments?.getString("email") ?: "Non renseigné"
        val hobbies = arguments?.getString("hobbies") ?: "Non renseigné"

        // print values
        view.findViewById<TextView>(R.id.tv_nom).text = "Nom : $nom"
        view.findViewById<TextView>(R.id.tv_prenom).text = "Prénom : $prenom"
        view.findViewById<TextView>(R.id.tv_date_naissance).text = "Date de naissance : $dateNaissance"
        view.findViewById<TextView>(R.id.tv_telephone).text = "Téléphone : $telephone"
        view.findViewById<TextView>(R.id.tv_email).text = "E-mail : $email"
        view.findViewById<TextView>(R.id.tv_interets).text = "Hobbies : $hobbies"

        // go back to homepage
        buttonGoBack.setOnClickListener {
            val fragment = HomePageFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
