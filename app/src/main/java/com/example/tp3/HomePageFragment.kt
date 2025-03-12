import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tp3.InscriptionFragment
import com.example.tp3.R

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_page_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnInscription = view.findViewById<Button>(R.id.buttonNouvelleInscription)
        val btnConnexion = view.findViewById<Button>(R.id.buttonConnexion)

        //When the button Nouvelle inscription is clicked, the InscriptionFragment is displayed
        btnInscription.setOnClickListener {
            val fragment = InscriptionFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

//        btnConnexion.setOnClickListener {
//            val fragment = ConnexionFragment()
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, fragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }



    }


}