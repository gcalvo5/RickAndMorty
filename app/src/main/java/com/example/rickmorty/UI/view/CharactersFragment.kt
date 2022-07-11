package com.example.rickmorty.UI.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.UI.viewmodel.CharacterViewModel
import com.example.rickmorty.data.adapters.AdapterCharacters
import com.example.rickmorty.databinding.FragmentCharactersBinding
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
 * Use the [CharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCharactersBinding
    private val characterViewModel:CharacterViewModel by viewModels()
    private lateinit var characterRecycler:RecyclerView
    lateinit var adapter:AdapterCharacters
    lateinit var layoutManager: LinearLayoutManager
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

        binding = FragmentCharactersBinding.inflate(inflater)
        characterRecycler = binding.recyclerCharacters
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        characterRecycler.layoutManager = layoutManager





        characterRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    //scrolled to BOTTOM
                    GlobalScope.launch (Dispatchers.Main){



                        if(!characterViewModel.characters.info.next.isNullOrEmpty()){
                            binding.charactersProgressBar.visibility = View.VISIBLE
                            characterViewModel.callForNextCharactersByUrl(characterViewModel.characters.info.next!!)
                            binding.charactersProgressBar.visibility = View.GONE
                        }




                    }

                } else if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    //scrolled to TOP
                }
            }
        })



        GlobalScope.launch(Dispatchers.Main) {
            binding.charactersProgressBar.visibility = View.VISIBLE
            characterViewModel.callForCharacterList()
            binding.charactersProgressBar.visibility = View.GONE
            characterRecycler.adapter = adapter
        }



        characterViewModel.characterViewModel.observe(viewLifecycleOwner, Observer {
            if (::adapter.isInitialized){
                Log.e("test", "character adapter inicializado")
                adapter.notifyDataSetChanged()

            }
            else{
                adapter = AdapterCharacters(it,characterViewModel.characterInit)
                characterRecycler.adapter = adapter
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
         * @return A new instance of fragment CharactersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}