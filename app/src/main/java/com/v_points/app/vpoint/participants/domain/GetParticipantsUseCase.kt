package com.v_points.app.vpoint.participants.domain

import com.v_points.app.vpoint.participants.data.ParticipantsRepository
import com.v_points.app.vpoint.participants.data.UserFirestoreModel
import javax.inject.Inject

class GetParticipantsUseCase @Inject constructor(
    private val participantsRepository: ParticipantsRepository
) {
    suspend operator fun invoke(): List<UserFirestoreModel> = participantsRepository.getUsers()
}
