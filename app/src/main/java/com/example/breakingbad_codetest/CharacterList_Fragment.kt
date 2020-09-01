package com.example.breakingbad_codetest

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad_codetest.databinding.FragmentCharlistBinding
import com.example.breakingbad_codetest.util.EspressoIdlingResource
import com.example.breakingbad_codetest.util.networkutils.LoadingState
import com.example.breakingbad_codetest.viewadapters.CharacterClick
import com.example.breakingbad_codetest.viewadapters.CharactersAdapter
import com.example.breakingbad_codetest.viewmodel.CharactersListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_charlist.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Fragment which displaying a Character List
 */
class CharacterList_Fragment : Fragment() {

    private var viewModelAdapter: CharactersAdapter? = null
    private lateinit var v: View

    /**
     * Here, by viewModel() creates the instance for the ViewModel and it will also resolve the dependency required by it.
     */
    private val viewModel:CharactersListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCharlistBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_charlist, container, false)
        v = binding.root.findViewById(R.id.CharacterList_Fragment_Layout)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewmodel = viewModel

        //Sets the adapter of the  RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        viewModelAdapter = CharactersAdapter(CharacterClick {
            viewModel.displayPropertyDetails(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewModelAdapter
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    CharacterList_FragmentDirections.actionCharlistFragmentToCharDetailFragment(it)
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })

        //For Search Title
        binding.root.findViewById<SearchView>(R.id.editTextSearch)
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    if (text != null) viewModel.setSearch(text.toString())
                    return false
                }
            })

        setHasOptionsMenu(true);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.app1 -> viewModel.setFilter(1)
                R.id.app2 -> viewModel.setFilter(2)
                R.id.app3 -> viewModel.setFilter(3)
                R.id.app4 -> viewModel.setFilter(4)
                R.id.app5 -> viewModel.setFilter(5)
            }
        return true
    }


    private fun setupObserver() {
        EspressoIdlingResource.increment()
        // observe the search items
        viewModel.allItemsSearch.observe(viewLifecycleOwner, Observer { items ->
            // update the displayed items when the search results change
            items.let { viewModelAdapter?.results = items }
            EspressoIdlingResource.decrement()
        })
        EspressoIdlingResource.increment()
        // observe the filtered items
        viewModel.allItemsFiltered.observe(viewLifecycleOwner, Observer { items ->
            // update the displayed items when the filtered results change
            items.let { viewModelAdapter?.results = items }
            EspressoIdlingResource.decrement()
        })
        EspressoIdlingResource.increment()
        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    Snackbar.make(v,
                        it.msg.toString(), Snackbar.LENGTH_LONG).show();
                    progressBar.visibility = View.GONE
                }
                LoadingState.Status.RUNNING -> {
                    progressBar.visibility = View.VISIBLE

                }
                LoadingState.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE

                }

            }
            EspressoIdlingResource.decrement()
        })
    }


}
