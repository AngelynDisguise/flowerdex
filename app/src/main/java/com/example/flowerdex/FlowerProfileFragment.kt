package com.example.flowerdex

import FlowerItem
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class FlowerProfileFragment : Fragment() {

    private var flowerItem: FlowerItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flower_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get Flower from bundle sent by FlowerFragment
        @Suppress("DEPRECATION")
        flowerItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("flower", FlowerItem::class.java)
        } else {
            arguments?.getParcelable("flower")
        }

        flowerItem?. let {
            Log.i("FlowerProfileFragment", "Flower profile received a flower: $it")
        } ?: Log.e("FlowerProfileFragment", "Flower profile received no flower :(\n arguments = $arguments")

        // Go back to Flowers Menu (FlowerFragment)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        // Change toolbar title to the selected flower name
        flowerItem?.let {
            Log.i("FlowerProfileFragment", "Set toolbar name to ${it.name}")
            (requireActivity() as MainActivity).setActionBarTitle(it.name)
        } ?: Log.e("FlowerProfileFragment", "Toolbar was not set in onResume.")

    }

}