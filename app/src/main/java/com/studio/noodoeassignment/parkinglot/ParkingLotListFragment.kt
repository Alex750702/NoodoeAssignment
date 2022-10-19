package com.studio.noodoeassignment.parkinglot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio.noodoeassignment.R
import com.studio.noodoeassignment.data.ParkInfoUI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ParkingLotListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ParkingLotListFragment : Fragment() {

    private lateinit var viewModel: ParkingLotViewModel
    private val adapter = ParkAdapter()

    private var mapHasCharger = mapOf<String, ParkInfoUI>()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProvider(this).get(ParkingLotViewModel::class.java)
        viewModel.getParkInfoUIList().observe(this) {
            adapter.setPark(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cbParkContainCharger = view.findViewById<CheckBox>(R.id.cbParkContainCharger)
        cbParkContainCharger.setOnCheckedChangeListener { p0, p1 ->

            if (p1) {
                mapHasCharger = viewModel.getParkInfoUIList().value!!.filter {
                    it.value.chargeStationCharging.isNotEmpty() ||
                            it.value.chargeStationIdle.isNotEmpty()
                }
                adapter.setPark(mapHasCharger)
            } else {
                adapter.setPark(viewModel.getParkInfoUIList().value!!.toMap())
            }
        }
        val btn = view.findViewById<Button>(R.id.btnNext)
        btn.setOnClickListener {
            findNavController().navigate(R.id.timeZoneFragment)
        }


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val dataList = view.findViewById<RecyclerView>(R.id.rvPark)
        dataList.layoutManager = layoutManager
        dataList.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ParkingLotListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ParkingLotListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}