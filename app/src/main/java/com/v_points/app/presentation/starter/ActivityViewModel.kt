package com.v_points.app.presentation.starter

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.v_points.app.domain.starter.GetThemePrefsUseCase
import com.v_points.app.ui_common.coroutines.launchJob
import com.v_points.app.ui_common.fragment.base.BaseViewModel
import com.v_points.app.vpoint.fiersote.data.repository.GymsRepository
import com.v_points.app.vpoint.fiersote.data.repository.RolesRepository
import com.v_points.app.vpoint.fiersote.data.repository.StatsRepository
import com.v_points.app.vpoint.participants.domain.GetParticipantsUseCase
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val getThemePrefsUseCase: GetThemePrefsUseCase,
    private val gymsRepository: GymsRepository,
    private val getParticipantsUseCase: GetParticipantsUseCase,
    private val rolesRepository: RolesRepository,
    private val statsRepository: StatsRepository,
) : BaseViewModel() {

    fun onCreateActivity(): Int = getThemePrefsUseCase()

    init {
        viewModelScope.launchJob(tryBlock = {
            val gyms = gymsRepository.getGyms()
            val participants = getParticipantsUseCase()
            val roles = rolesRepository.getRoles()
            val stats = statsRepository.getStats()

            Log.d("FireStoreCheckDate", "---Gyms---")
            gyms.forEach {
                Log.d("FireStoreCheckDate", it.toString())
            }
            Log.d("FireStoreCheckDate", "---Participants---")
            participants.forEach {
                Log.d("FireStoreCheckDate", it.toString())
            }
            Log.d("FireStoreCheckDate", "---Roles---")
            roles.forEach {
                Log.d("FireStoreCheckDate", it.toString())
            }
            Log.d("FireStoreCheckDate", "---Stats---")
            stats.forEach {
                Log.d("FireStoreCheckDate", it.toString())
            }
        }, catchBlock = { throwable ->
            Log.d("FireStoreCheckDate", throwable.toString())
            throwable.printStackTrace()
        })
    }
}