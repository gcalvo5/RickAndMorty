package com.example.rickmorty.UI.view.tabsfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rickmorty.R
import com.example.rickmorty.UI.viewmodel.LocationViewModel
import com.example.rickmorty.databinding.FragmentOriginTabBinding
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
 * Use the [OriginTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class OriginTabFragment(val url:String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentOriginTabBinding
    private val locationViewModel: LocationViewModel by viewModels()
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        binding = FragmentOriginTabBinding.inflate(layoutInflater)
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("test","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            binding.OriginTabProgressBar.visibility = View.VISIBLE
            locationViewModel.callForLocationByUrl(url)
            binding.OriginTabProgressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        locationViewModel.locationViewModel.observe(viewLifecycleOwner, Observer {
            setUpTab(it.locations.lastOrNull()?.name, it.locations.lastOrNull()?.type, it.locations.lastOrNull()?.dimension)



            binding.originTabLayout.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    navController = Navigation.findNavController(view!!)

                    var bundle = Bundle()


                    bundle.putString("name", it.locations.lastOrNull()?.name)
                    bundle.putString("type", it.locations.lastOrNull()?.type)
                    bundle.putString("dimension", it.locations.lastOrNull()?.dimension)
                    bundle.putStringArray("residents", it.locations.lastOrNull()?.residents?.toTypedArray())
                    if(!it.locations.lastOrNull()?.name.isNullOrEmpty()) {
                        navController.navigate(R.id.nav_location, bundle)
                    }
                }

            })
        })




        // Inflate the layout for this fragment
        return binding.root
    }

    fun setUpTab(name: String?, type:String?, dimension:String?){
        binding.originName.text = name
        binding.originUrl.text = type
        binding.originDimension.text = dimension
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OriginTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OriginTabFragment("").apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}