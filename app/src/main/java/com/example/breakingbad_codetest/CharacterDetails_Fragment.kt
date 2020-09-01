package com.example.breakingbad_codetest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.breakingbad_codetest.databinding.FragmentChardetailBinding
import com.example.breakingbad_codetest.viewmodel.CharacterDetailViewModel
import com.example.breakingbad_codetest.viewmodel.CharactersListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

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