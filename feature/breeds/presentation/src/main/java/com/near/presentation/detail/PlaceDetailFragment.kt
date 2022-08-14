package com.near.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.near.common.domain.utils.onSuccess
import com.near.common.presentation.bindings.loadImage
import com.near.common.presentation.extension.launchAndRepeatWithViewLifecycle
import com.near.presentation.R
import com.near.presentation.databinding.FragmentPlaceDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PlaceDetailFragment : BottomSheetDialogFragment() {

    private val viewModel: PlaceDetailViewModel by viewModels()
    lateinit var binding: FragmentPlaceDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            binding.retryButton.setOnClickListener {
                viewModel.getPlaceDetails()
            }
        }
        collectPlaceDetails()
    }

    private fun collectPlaceDetails() {
        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.placeDetail.collect { result ->
                    Timber.d(result.toString())
                    result.onSuccess { detail ->
                        binding.run {
                            addressText.text = detail.address
                            nameText.text = detail.name
                            headerImage.loadImage(
                                detail.icon,
                            )
                            tvCategory.text = detail.categoryName
                            tvInformation.text = resources.getString(
                                R.string.place_information,
                                detail.timezone,
                                detail.geocodes.main.latitude.toString(),
                                detail.geocodes.main.longitude.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}