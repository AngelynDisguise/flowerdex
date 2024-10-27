package com.example.flowerdex.flowerProfileTabs

import FlowerItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flowerdex.R

class FlowerEcologyFragment : Fragment() {

    private val viewModel: FlowerProfileViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flower_ecology, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use LiveData to observe ViewModel data
        viewModel.flowerItem.observe(viewLifecycleOwner) { flower: FlowerItem ->
            // ... then update UI with flower data here
            val textView: TextView = view.findViewById(R.id.flower_ecology_text_view)
            textView.text = "Ecology of ${flower.name}..."
        }
    }
}