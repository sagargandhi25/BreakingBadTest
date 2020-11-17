package com.example.breakingbad_codetest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breakingbad_codetest.databinding.FragmentChardetailBinding
import com.example.breakingbad_codetest.viewmodel.CharacterDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Fragment which displaying a Character Details
 */
class CharacterDetails_Fragment :Fragment(){

    val viewModel by viewModel<CharacterDetailViewModel>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentChardetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val characterProperty = CharacterDetails_FragmentArgs.fromBundle(arguments!!).selectedProperty
        binding.viewModel = viewModel
        viewModel.setProperty(characterProperty)

        return binding.root

    }
}