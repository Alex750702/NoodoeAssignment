package com.studio.noodoeassignment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.studio.noodoeassignment.MainActivity
import com.studio.noodoeassignment.R
import com.studio.noodoeassignment.data.LogInRequest


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogInFragment : Fragment() {


    private lateinit var viewModel: LogInViewModel

    private lateinit var tvError: TextView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        viewModel.getLogInResult().observe(this) {
            (requireActivity() as MainActivity).userInfo = it
            findNavController().navigate(R.id.parkingLotListFragment)
        }
        viewModel.getErrorMessage().observe(this) {
            tvError.text = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvError = view.findViewById(R.id.tvError)
        val btn = view.findViewById<Button>(R.id.btnLogIn)
        btn.setOnClickListener {

            val userName = view.findViewById<EditText>(R.id.etUserName)
            val passWord = view.findViewById<EditText>(R.id.etPassWord)

            val logInRequest = LogInRequest()
            logInRequest.password = passWord.text.toString()
            logInRequest.username = userName.text.toString()

            viewModel.fnLogIn(logInRequest)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LogInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}