package com.ubaya.sushiworld.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.sushiworld.databinding.SushiListItemBinding
import com.ubaya.sushiworld.model.Sushi

class SushiListAdapter(val sushiList:ArrayList<Sushi>): RecyclerView.Adapter<SushiListAdapter.SushiViewHolder>(){
    class SushiViewHolder(var binding:SushiListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SushiViewHolder {
        val binding = SushiListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SushiViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sushiList.size
    }

    override fun onBindViewHolder(holder: SushiViewHolder, position: Int) {
        holder.binding.txtSushiName.text = sushiList[position].name
        holder.binding.txtSushiDescription.text = sushiList[position].description
        holder.binding.txtAuthor.text = sushiList[position].author

        val url = sushiList[position].images
        val builder = Picasso.Builder(holder.binding.root.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(url).into(holder.binding.imageView)

        holder.binding.btnDetail.setOnClickListener{
            val action = SushiListFragmentDirections.actionSushiDetailFragment(sushiList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateSushiList(newSubjectList: ArrayList<Sushi>) {
        sushiList.clear()
        sushiList.addAll(newSubjectList)
        notifyDataSetChanged()
    }
}