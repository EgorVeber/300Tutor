package org.threehundredtutor.data.registration

import org.threehundredtutor.common.BASE_KURSBIO_URL
import org.threehundredtutor.data.registration.mappers.toRegistrationModelMapper
import org.threehundredtutor.data.registration.models.RegisterParams
import org.threehundredtutor.data.registration.models.RegisterResponse
import org.threehundredtutor.domain.registration.models.RegistrationModel
import org.threehundredtutor.domain.registration.models.RegistrationParams
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationRepositoryImpl {

    private lateinit var retrofit: Retrofit

    private val createService: RegistrationService
        get() = getClient(BASE_KURSBIO_URL).create(RegistrationService::class.java)

    suspend fun registerUser(params: RegistrationParams): RegistrationModel {
        val registerParams = RegisterParams(
            email = params.email,
            noEmail = params.noEmail,
            name = params.name,
            surname = params.surname,
            patronymic = params.patronymic,
            phoneNumber = params.phoneNumber,
            noPhoneNumber = params.noPhoneNumber,
            password = params.password
        )
        val response = createService.register(registerParams)
        return extractValue(response)?.toRegistrationModelMapper() ?: throw Exception()
    }

    private fun extractValue(response: Response<RegisterResponse>): RegisterResponse? {
        return response.body()
    }

    private fun getClient(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
