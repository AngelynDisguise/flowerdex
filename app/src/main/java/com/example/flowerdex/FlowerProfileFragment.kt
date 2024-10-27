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
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.flowerdex.flowerProfileTabs.AboutFlowerFragment
import com.example.flowerdex.flowerProfileTabs.FlowerEcologyFragment
import com.example.flowerdex.flowerProfileTabs.HowToGrowFlowerFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class FlowerProfileFragment : Fragment() {

    private var flowerItem: FlowerItem? = null  // the flower data for all pages

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flower_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Retrieve FlowerItem bundle (with key "flower") sent by FlowerFragment */
        @Suppress("DEPRECATION")
        flowerItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("flower", FlowerItem::class.java) // newer API parcelable, more type-safe
        } else {
            arguments?.getParcelable("flower")
        }

        flowerItem?. let {
            Log.i("FlowerProfileFragment", "Flower profile received a flower: $it\n")
            // If all goes well, do logic stuff with the FlowerItem data here...
        } ?: Log.e("FlowerProfileFragment", "Flower profile received no flower :(\n arguments = $arguments")

        /* Setup tabs and horizontal paging with child fragments (i.e. About, HowToGrow, Ecology) */
        val pagerAdapter = FlowerProfilePagerAdapter(this)  // creates the fragment pages
        val viewPager: ViewPager2 = view.findViewById(R.id.flower_profile_view_pager)  // holds the pages
        val tabLayout: TabLayout = view.findViewById(R.id.flower_profile_tab_layout)

        viewPager.adapter = pagerAdapter // attach the adapter to the ViewPager2
        // Link TabLayout and ViewPager2 together - tabs will synchronize with pager position
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "How To Grow"
                2 -> "Ecology"
                else -> null
            }
        }.attach()

        /* Navigate back to Flowers Menu (FlowerFragment) */
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        /* Change toolbar title to the selected flower name */
        flowerItem?.let {
            Log.i("FlowerProfileFragment", "Set toolbar name to ${it.name}")
            (requireActivity() as MainActivity).setActionBarTitle(it.name)
        } ?: Log.e("FlowerProfileFragment", "Toolbar was not set in onResume.")

    }
}

/*
* This pager adapter generates the pages (i.e. the fragments) that the ViewPager2 shows.
*
* Sidenote: The original ViewPager and FragmentPagerAdapter/FragmentStatePagerAdapter adapter are deprecated,
* so we use ViewPager2 and FragmentStateAdapter instead.
* */
class FlowerProfilePagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFlowerFragment()
            1 -> HowToGrowFlowerFragment()
            2 -> FlowerEcologyFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}