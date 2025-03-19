import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tp3.DatabaseUserHelper
import com.example.tp3.R
import com.example.tp3.DatabasePlaningHelper

class PlaningDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.planing_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get session information
        val sharedPreferences =
            requireActivity().getSharedPreferences("session", android.content.Context.MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("user_login", null)

        Toast.makeText(requireContext(), userLogin, Toast.LENGTH_SHORT).show()
        // if the user is connected, we display all his planning information
        if (userLogin != null) {
            //get planning information from the database
            val databasePlaningHelper = DatabasePlaningHelper(requireContext())
            val planning = databasePlaningHelper.getPlaning(userLogin)

            // display planning information
            view.findViewById<android.widget.TextView>(R.id.textViewSlot1).text =
                "8h-10h :" + planning.slot1
            view.findViewById<android.widget.TextView>(R.id.textViewSlot2).text =
                "10h-12h :" + planning.slot2
            view.findViewById<android.widget.TextView>(R.id.textViewSlot3).text =
                "14h-16h :" + planning.slot3
            view.findViewById<android.widget.TextView>(R.id.textViewSlot4).text =
                "16h-18h :" + planning.slot4

        } else {
            Toast.makeText(
                requireContext(),
                "Vous devez vous connecter pour accéder à cette page",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}