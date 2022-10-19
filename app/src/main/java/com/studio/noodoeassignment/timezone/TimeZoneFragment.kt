package com.studio.noodoeassignment.timezone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.studio.noodoeassignment.MainActivity
import com.studio.noodoeassignment.R
import com.studio.noodoeassignment.data.LogInResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimeZoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeZoneFragment : Fragment() {

    lateinit var userInfo: LogInResponse
    lateinit var rgTimeZoneContainer: RadioGroup
    lateinit var tvUserEmail: TextView
    lateinit var tvUserTimeZone: TextView
    lateinit var tvChangeTimeZone: TextView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_zone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgTimeZoneContainer = view.findViewById(R.id.rgTimeZoneContainer)
        tvUserEmail = view.findViewById(R.id.tvUserEmail)
        tvUserTimeZone = view.findViewById(R.id.tvUserTimeZone)
        tvChangeTimeZone = view.findViewById(R.id.tvChangeTimeZone)


        rgTimeZoneContainer.setOnCheckedChangeListener { radioGroup: RadioGroup, id: Int ->
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            val rbSelected = view.findViewById<RadioButton>(checkedRadioButtonId)
            userInfo.timezone = rbSelected.text.toString()
        }

        userInfo = (requireActivity() as MainActivity).userInfo
        tvUserEmail.text = userInfo.name
        val userTimeZone = "Time zone = ${userInfo.timezone}"
        tvUserTimeZone.text = userTimeZone

        val btn = view.findViewById<Button>(R.id.btnNext)
        btn.setOnClickListener {
            findNavController().navigate(R.id.parkingLotListFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimeZoneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeZoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}