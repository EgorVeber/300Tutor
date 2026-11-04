package com.v_points.app.vpoint.participants.domain

import com.v_points.app.vpoint.fiersote.domain.UserModel
import com.v_points.app.vpoint.participants.data.ParticipantsRepository
import javax.inject.Inject

class GetParticipantsUseCase @Inject constructor(
    private val participantsRepository: ParticipantsRepository
) {
    suspend operator fun invoke(): List<UserModel> = participantsRepository.getUsers()
}
