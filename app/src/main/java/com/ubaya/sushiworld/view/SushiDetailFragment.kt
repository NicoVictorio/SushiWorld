package com.ubaya.sushiworld.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.sushiworld.R
import com.ubaya.sushiworld.databinding.FragmentSushiDetailBinding
import com.ubaya.sushiworld.viewmodel.SushiDetailViewModel

class SushiDetailFragment : Fragment() {
    private lateinit var binding:FragmentSushiDetailBinding
    private lateinit var viewModel: SushiDetailViewModel
    private var currentParagraph = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSushiDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sushiId = SushiDetailFragmentArgs.fromBundle(requireArguments()).sushiId

        viewModel = ViewModelProvider(this).get(SushiDetailViewModel::class.java)
        viewModel.fetch(sushiId)

        viewModel.sushiLD.observe(viewLifecycleOwner, Observer { sushi ->
            binding.txtSushiDetailName.text = sushi.name
            binding.txtSushiDetailAuthor.text = sushi.author
            binding.txtSushiDetailParagraphs.text = sushi?.paragraphs?.get(currentParagraph)

            sushi.images?.let {
                Picasso.get().load(it).into(binding.imgView)
            }
        })

        binding.btnNext.setOnClickListener {
            val sushi = viewModel.sushiLD.value
            Log.d("sushi", sushi.toString())
            currentParagraph++
            if (sushi != null && currentParagraph < sushi.paragraphs?.size ?: 0) {
                updateParagraph()
            } else {
                Log.d("hai", "hai")
                Toast.makeText(requireContext(), "Ini adalah paragraf terakhir", Toast.LENGTH_SHORT).show()
                currentParagraph--
            }
        }

        binding.btnPrev.setOnClickListener {
            if (currentParagraph > 0) {
                currentParagraph--
                updateParagraph()
            } else {
                Toast.makeText(requireContext(), "Ini adalah paragraf pertama", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateParagraph() {
        val sushi = viewModel.sushiLD.value
        Log.d("paragrafskrg", currentParagraph.toString())
        Log.d("totalparagraf", sushi?.paragraphs?.size.toString())
        binding.txtSushiDetailParagraphs.text = sushi?.paragraphs?.get(currentParagraph)
    }
}