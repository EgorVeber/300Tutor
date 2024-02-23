package org.threehundredtutor.junit.mockk_date

import javax.inject.Inject

class GetAllowedCountriesUseCase @Inject constructor() {
    operator fun invoke(): List<AllowedCountry> =
        getAllowedCountryList().mapIndexed { index, name ->
            AllowedCountry(
                name = name,
                id = getAllowedIdCountry()[index],
                top = getIndex(index),
                countryCode = getAllowedCode()[index]
            )
        }
}

data class AllowedCountry(
    val name: String,
    val id: String,
    val top: Boolean = false,
    val countryCode: String
)

fun getIndex(index: Int) = index % 2 == 0
