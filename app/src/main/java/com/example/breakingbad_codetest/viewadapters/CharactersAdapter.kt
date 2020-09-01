package com.example.breakingbad_codetest.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad_codetest.R
import com.example.breakingbad_codetest.database.DatabaseCharacter
import com.example.breakingbad_codetest.databinding.RowCharacterBinding


class CharactersAdapter (val callback: CharacterClick): RecyclerView.Adapter<CharactersViewHolder>(){

    /**
     * The characters that our Adapter will show
     */
    var results: List<DatabaseCharacter> = emptyList()
        set(value) {
            field = value
            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val withDataBinding: RowCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CharactersViewHolder.LAYOUT,
            parent,
            false
        )
        return CharactersViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.results = results[position]
            //To handle onclick
            it.resultsCallback = callback

        }
    }
}
    /**
     * ViewHolder for character items. All work is done by data binding.
     */
    class CharactersViewHolder(val viewDataBinding: RowCharacterBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.row_character
        }
    }

/**
 * Click listener for Character. By giving the block a name it helps a reader understand what it does.
 *
 */
class CharacterClick(val block: (DatabaseCharacter) -> Unit) {
    /**
     * Called when a character is clicked
     *
     * @param character the character that was clicked
     */
    fun onClick(character: DatabaseCharacter) = block(character)
}
