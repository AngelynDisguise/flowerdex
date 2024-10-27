package com.example.flowerdex

import FlowerGridAdapter
import FlowerItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
*/
class FlowerFragment : Fragment() {

    private val adapter: FlowerGridAdapter = FlowerGridAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.gridRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        recyclerView.adapter = adapter

        // some data
        val flowerList = listOf(
            FlowerItem(1, "Flower1", R.drawable.rose),
            FlowerItem(2, "Flower2", R.drawable.rose),
            FlowerItem(3, "Flower3", R.drawable.rose),
            FlowerItem(4, "Flower4", R.drawable.rose),
            FlowerItem(5, "Flower5", R.drawable.rose),
            FlowerItem(6, "Flower6", R.drawable.rose),
            FlowerItem(7, "Flower7", R.drawable.rose),
            FlowerItem(8, "Flower8", R.drawable.rose),
        )

        adapter.submitList(flowerList) // Send list to list adapter

        adapter.setOnClickListener { _, flowerItem ->
            val flowerBundle = Bundle().apply { putParcelable("flower", flowerItem) }
            findNavController().navigate(R.id.flowerProfileFragment, flowerBundle) // Go to flower profile
        }
    }

}