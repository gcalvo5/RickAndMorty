package com.example.rickmorty.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharacterInit
import com.example.rickmorty.data.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(val characterInit: CharacterInit) : ViewModel() {
    var characterViewModel = MutableLiveData<List<Character>>(characterInit.characterList)
    var characters:Characters = characterInit.characters
    suspend fun callForCharacterList(){
        characterInit.createCharacterList()
        characterViewModel.postValue(characterInit.characterList)
        characters = characterInit.characters

    }
    suspend fun callForCharacterListByUrls(urls:Array<String>){

        for (i in urls){
            characterInit.createCharacterListByUrls(i)
        }

        characterViewModel.postValue(characterInit.characterList)
    }
    suspend fun callForNextCharactersByUrl(url:String){
        characterInit.getCharactersNextPageByUrl(url)
        characters = characterInit.characters
        characterViewModel.postValue(characterInit.characterList)
    }
    fun cleanCharacterList(){
        characterInit.characterList  = mutableListOf<Character>()
    }
}