package com.example.flowerdex.flowerProfileTabs

import FlowerItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A [ViewModel] for `FlowerProfileFragment` (the ViewModelStoreOwner).
 * Child fragments of `FlowerProfileFragment` reference their parent
 * to access this ViewModel and observe the same flower data here.
 *
 * As the 'Single Source of Truth', flower data is encapsulated and
 * only [FlowerProfileViewModel] can have control over LivaData,
 * making LiveData at the observer-level immutable and read-only.
 */
class FlowerProfileViewModel : ViewModel() {
    private val _flowerItem = MutableLiveData<FlowerItem>() // only this ViewModel can mutate this
    val flowerItem: LiveData<FlowerItem> = _flowerItem  // observe this

    // Atomic update of flower data (preventing race conditions)
    fun setFlowerItem(flower: FlowerItem) {
        _flowerItem.value = flower
    }
}