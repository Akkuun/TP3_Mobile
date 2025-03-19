import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tp3.DatabaseUserHelper
import com.example.tp3.R

class PlaningDetailFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.planing_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("session", android.content.Context.MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("user_login", null)

        // if the user is connected, we display all his planning information
        if (userLogin != null) {
            //get planning information from the database
            val databaseUserHelper = DatabaseUserHelper(requireContext())
            val planning = databaseUserHelper.getPlanning(userLogin)

            Toast.makeText(requireContext(), planning.toString(), Toast.LENGTH_SHORT).show()


        } else {
           Toast.makeText(requireContext(), "Vous devez vous connecter pour accéder à cette page", Toast.LENGTH_SHORT).show()
        }
    }
}