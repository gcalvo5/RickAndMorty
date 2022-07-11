package com.example.rickmorty.UI.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.UI.viewmodel.LocationViewModel
import com.example.rickmorty.data.adapters.AdapterLocations
import com.example.rickmorty.databinding.FragmentLocationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LocationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LocationsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding:FragmentLocationsBinding
    private val locationViewModel:LocationViewModel by viewModels()
    lateinit var adapter:AdapterLocations

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

        binding = FragmentLocationsBinding.inflate(inflater)
        val recyclerView:RecyclerView = binding.locationsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)


        GlobalScope.launch (Dispatchers.Main){
            locationViewModel.callForAllLocations()

        }


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    //scrolled to BOTTOM
                    GlobalScope.launch (Dispatchers.Main){



                        if(!locationViewModel.locations.info.next.isNullOrEmpty()){
                            locationViewModel.callForAllLocationsNextPageById(locationViewModel.locations.info.next)
                        }




                    }

                } else if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    //scrolled to TOP
                }
            }
        })




        locationViewModel.locationsListViewModel.observe(viewLifecycleOwner, Observer {
            if(::adapter.isInitialized){
                adapter.notifyDataSetChanged()
            }
            else{
                adapter = AdapterLocations(it)
                recyclerView.adapter = adapter
            }




        })


        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LocationsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}