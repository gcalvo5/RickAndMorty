package com.example.rickmorty.UI.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rickmorty.R
import com.example.rickmorty.UI.viewmodel.CharacterViewModel
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.databinding.FragmentEpisodeBinding
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
 * Use the [EpisodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EpisodeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var binding:FragmentEpisodeBinding
    private val characterViewModel:CharacterViewModel by viewModels()
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        binding = FragmentEpisodeBinding.inflate(layoutInflater)
        val episodeCharactersUrls = arguments?.getStringArray("characters")
        GlobalScope.launch (Dispatchers.Main){
            if (episodeCharactersUrls != null) {
                characterViewModel.cleanCharacterList()
                binding.EpisodeCharactersProgressBar.visibility = View.VISIBLE
                characterViewModel.callForCharacterListByUrls(episodeCharactersUrls)
                binding.EpisodeCharactersProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        characterViewModel.cleanCharacterList()
        val episodeName = arguments?.getString("episodeName")
        val episodeAirDate = arguments?.getString("airDate")
        val episodeEpisode = arguments?.getString("episodeEpisode")




        characterViewModel.characterViewModel.observe(viewLifecycleOwner, Observer {

            if (episodeName != null && episodeAirDate != null && episodeEpisode != null) {
                characterViewModel.cleanCharacterList()
                setUpFragment(episodeName,episodeAirDate,episodeEpisode,it)

            }
        })





        // Inflate the layout for this fragment
        return binding.root
    }


    private fun setUpFragment(episodeName:String,episodeAirDate:String,episodeEpisode:String,episodeCharacters:List<Character>){

        binding.episodeAirDate.text = episodeAirDate
        binding.episodeName.text = episodeName
        binding.episodeEpisode.text = episodeEpisode

        for(character in episodeCharacters){
            var textView:TextView = TextView(context)

            textView.text = character.name
            textView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    navController = Navigation.findNavController(view!!)

                    var bundle = Bundle()
                    bundle.putString("id", character.id)
                    bundle.putString("name", character.name)
                    bundle.putString("status", character.status)
                    bundle.putString("species", character.species)
                    bundle.putString("type", character.type)
                    bundle.putString("gender", character.gender)
                    bundle.putString("originUrl", character.origin.url)
                    bundle.putString("image", character.image)
                    bundle.putStringArrayList("episodesUrls", character.episode)
                    navController.navigate(R.id.nav_character, bundle)
                }

            })
            binding.episodeCharactersLinearLayout.addView(textView)
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EpisodeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EpisodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}