import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tp3.DatabaseHelper
import com.example.tp3.PlaningFragment
import com.example.tp3.R

class ConnexionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.connexion_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = DatabaseHelper(requireContext())

        val btnConnexion = view.findViewById<Button>(R.id.buttonLogin);
        val btnRetour = view.findViewById<Button>(R.id.buttonRetour);

        btnRetour.setOnClickListener() {

            val fragment = HomePageFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnConnexion.setOnClickListener() {
            val login = view.findViewById<EditText>(R.id.editTextUsername)?.text.toString()
            val password = view.findViewById<EditText>(R.id.editTextPassword)?.text.toString()

            Toast.makeText(requireContext(), databaseHelper.checkUser(login, password).toString(), Toast.LENGTH_SHORT).show()
            // if the credentials are correct, we go to the planning page
            if (databaseHelper.checkUser(login, password)) {
                // we put the login in the shared preferences
                val sharedPreferences = requireActivity().getSharedPreferences("session", android.content.Context.MODE_PRIVATE)
                sharedPreferences.edit()
                    .putString("user_login", login)
                    .apply()

                val fragment = PlaningFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
         }


    }

}