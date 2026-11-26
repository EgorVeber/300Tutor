package com.v_points.app.vpoint.games.domain

import com.v_points.app.vpoint.fiersote.domain.ServeStatModel
import com.v_points.app.vpoint.games.data.ServeStatsRepository
import javax.inject.Inject

class GetAllServeStatsUseCase @Inject constructor(
    private val serveStatsRepository: ServeStatsRepository
) {
    suspend operator fun invoke(): List<ServeStatModel> {
        return serveStatsRepository.getAllServeStats()
    }
}
