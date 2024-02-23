package org.threehundredtutor.junit.mockk_date

import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor() {
    operator fun invoke(): List<GeoCountry> =
        getAllCountryList().mapIndexed { index, name ->
            GeoCountry(name = name, id = getAllIdCountry()[index], countryCode = getAllCode()[index] )
        }
}

data class GeoCountry(
    val name: String,
    val id: String,
    val top: Boolean = false,
    val countryCode: String,
)

data class GeoCountryModel(
    val name: String,
    val id: String,
    val top: Boolean,
    val countryCode: String,
)

fun GeoCountry.toGeoCountryModel(service: String) = GeoCountryModel(
    name = name,
    id = id,
    top = top,
    countryCode = countryCode
)