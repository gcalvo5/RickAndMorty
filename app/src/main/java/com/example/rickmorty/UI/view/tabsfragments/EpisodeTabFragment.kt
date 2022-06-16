package com.example.rickmorty.UI.view.tabsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.UI.viewmodel.EpisodeViewModel
import com.example.rickmorty.data.adapters.AdapterTabEpisodes
import com.example.rickmorty.databinding.FragmentEpisodeTabBinding
import com.example.rickmorty.databinding.FragmentInfoTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Url

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EpisodeTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EpisodeTabFragment(val url: ArrayList<String>) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentEpisodeTabBinding

    private val episodeViewModel : EpisodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        binding = FragmentEpisodeTabBinding.inflate(layoutInflater)
        val progressBar:ProgressBar = binding.episodeTabProgressBar
        GlobalScope.launch (Dispatchers.Main){
            progressBar.visibility = View.VISIBLE
            episodeViewModel.callForEpisodesByUrls(url)
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val recyclerView:RecyclerView = binding.CharacterstabRecycler

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)



        episodeViewModel.episodeViewModel.observe(viewLifecycleOwner, Observer {
            var adapter:AdapterTabEpisodes = AdapterTabEpisodes(it.episodes)
            recyclerView.adapter = adapter
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
         * @return A new instance of fragment EpisodeTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EpisodeTabFragment(arrayListOf("")).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}