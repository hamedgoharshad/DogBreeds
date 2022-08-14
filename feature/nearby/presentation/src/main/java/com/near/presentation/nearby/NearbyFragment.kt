package com.near.presentation.nearby

import android.Manifest
import android.app.Activity
import android.content.IntentSender
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.SettingsClient
import com.google.android.material.snackbar.Snackbar
import com.near.common.domain.utils.onFailure
import com.near.common.presentation.extension.DefaultNavOptions
import com.near.common.presentation.extension.hasPermissions
import com.near.common.presentation.extension.launchAndRepeatWithViewLifecycle
import com.near.common.presentation.properties.autoCleared
import com.near.common.presentation.properties.viewBinding
import com.near.domain.model.Place
import com.near.presentation.R
import com.near.presentation.databinding.FragmentNearbyBinding
import com.near.presentation.nearby.adapter.NearbyListAdapter
import com.near.presentation.nearby.adapter.PlaceLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class NearbyFragment : Fragment(R.layout.fragment_nearby) {
    private val viewModel: NearbyViewModel by viewModels()
    private var adapter by autoCleared<NearbyListAdapter>()
    private val binding by viewBinding(FragmentNearbyBinding::bind)
    private val locationPermissions by lazy {
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    @Inject
    lateinit var locationSettingsRequest: LocationSettingsRequest

    @Inject
    lateinit var settingsClient: SettingsClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding()
        updateLocationWithPermission()
    }

    private fun getNearbyPlaces() {
        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.location.collectLatest { result ->
                    result.onFailure {
                        handleException(it)
                    }
                }
            }
            launch {
                viewModel.nearbyPlaces.collectLatest { result: PagingData<Place> ->
                    Timber.d("places- ${result.map { it.locationName }} }")
                    binding.srlRefreshPlaces.isRefreshing = false
                    binding.pbLoading.isVisible = false
                    adapter.submitData(result)
                }
            }
        }
    }

    private fun updateLocationWithPermission() {
        when {
            hasPermissions(locationPermissions) -> {
                updateLocationWithSettingsCheck()
            }
            shouldShowRequestPermissionRationale(locationPermissions[0]) ||
                    shouldShowRequestPermissionRationale(locationPermissions[1]) -> {
                findNavController().navigate(R.id.permission_fragment)
            }
            else -> {
                permissionLauncher.launch(locationPermissions)
            }
        }
    }

    private fun updateLocationWithSettingsCheck() {
        settingsClient.checkLocationSettings(locationSettingsRequest).apply {
            addOnSuccessListener {
                getNearbyPlaces()
            }
            addOnFailureListener { exception ->
                handleException(exception)
            }
        }
    }

    private fun setupBinding() {
        binding.run {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = buildAdapter()
            srlRefreshPlaces.setOnRefreshListener {
                adapter.refresh()
            }
            retryButton.setOnClickListener {
                adapter.retry()
            }
        }
    }

    private fun buildAdapter() =
        NearbyListAdapter { place ->
            findNavController().navigate(
                Uri.parse(PLACE_DETAIL_DEEP_LINK + place.id), DefaultNavOptions
            )
        }.apply {
            addLoadStateListener { loadState: CombinedLoadStates ->
                Timber.d("loadstate  : $loadState")
                val error = (loadState.refresh as? LoadState.Error)?.error
                with(binding) {
                    pbLoading.isVisible = loadState.refresh is LoadState.Loading || itemCount == 0
                    gPendingGroup.isVisible = loadState.refresh is LoadState.Error && itemCount == 0
                    tvPendingMsg.text = error?.message.toString()
                }
                if (itemCount != 0 && error != null)
                    showErrorMsg(
                        error.message.toString()
                    )
            }
            binding.rvNearbyPlacesList.adapter = withLoadStateHeaderAndFooter(
                header = PlaceLoadStateAdapter(::retry),
                footer = PlaceLoadStateAdapter(::retry)
            )
        }

    private fun handleException(throwable: Throwable) {
        Timber.e("exception : ${throwable.message}")
        if (throwable is ResolvableApiException) {
            try {
                val intentSenderRequest =
                    IntentSenderRequest.Builder(throwable.resolution).build()
                resolutionForResult.launch(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                showMessage(getString(R.string.msg_error_gps))
            }
        } else if (throwable is SecurityException) {
            permissionLauncher.launch(locationPermissions)
        }
    }

    private val resolutionForResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                updateLocationWithPermission()
            } else {
                findNavController().navigate(R.id.permission_fragment)
            }
        }

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            // should continue without permission ?
            // if (locationPermissions.all { result[it] == true }) {
            updateLocationWithSettingsCheck()
            // }
        }

    private fun showErrorMsg(
        message: String
    ) {
        Snackbar.make(
            binding.clContainer,
            message,
            Snackbar.LENGTH_LONG
        ).setAction(R.string.label_retry) {
            adapter.retry()
        }.show()
    }

    private fun showMessage(message: String) =
        Snackbar.make(binding.clContainer, message, Snackbar.LENGTH_INDEFINITE).show()

    companion object {
        const val PLACE_DETAIL_DEEP_LINK = "nearby://placeDetail/?id="
    }

}
