package com.example.rickmorty.UI.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.rickmorty.UI.view.tabsfragments.EpisodeTabFragment
import com.example.rickmorty.UI.view.tabsfragments.InfoTabFragment
import com.example.rickmorty.UI.view.tabsfragments.OriginTabFragment
import com.example.rickmorty.UI.viewmodel.CharacterViewModel
import com.example.rickmorty.UI.viewmodel.TabsAdapter.ViewPagerAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Character.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class Character : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding:FragmentCharacterBinding

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
        binding = FragmentCharacterBinding.inflate(inflater)
        setUpTabs()
        binding.textView.text = arguments?.getString("name")
        Picasso.get().load(arguments?.getString("image")).into(binding.imageView)



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        val viewPager:ViewPager = binding.viewPager
        adapter.addFragment(InfoTabFragment(arguments?.getString("name")!!,arguments?.getString("status")!!,arguments?.getString("species")!!,arguments?.getString("type")!!,arguments?.getString("gender")!!),"Info")
        adapter.addFragment(OriginTabFragment(arguments?.getString("originUrl")!!),"Origin")
        adapter.addFragment(EpisodeTabFragment(arguments?.getStringArrayList("episodesUrls")!!),"Episodes")
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 10
        binding.tabs.setupWithViewPager(viewPager)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Character.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Character().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}