package org.threehundredtutor.junit

import org.threehundredtutor.junit.mockk_date.AllowedCountry
import org.threehundredtutor.junit.mockk_date.GeoCountry
import org.threehundredtutor.junit.mockk_date.GeoCountryModel
import org.threehundredtutor.junit.mockk_date.GetAllCountriesUseCase
import org.threehundredtutor.junit.mockk_date.GetAllowedCountriesUseCase
import org.threehundredtutor.junit.mockk_date.GetServiceUseCase
import org.threehundredtutor.junit.mockk_date.RegistrationChoice
import org.threehundredtutor.junit.mockk_date.RegistrationChoiceType
import org.threehundredtutor.junit.mockk_date.toGeoCountryModel
import javax.inject.Inject

class GetRegistrationChoiceItemsByTypeScenario @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getAllowedCountriesUseCase: GetAllowedCountriesUseCase,
    private val getServiceUseCase: GetServiceUseCase
) {
    operator fun invoke(
        selectedCountryId: String,
        type: RegistrationChoiceType,
        whiteList: List<String>,
        blackList: List<String>,
    ): List<RegistrationChoice> {
        val service: String = getServiceUseCase()
        val allowedCountries: List<AllowedCountry> = getAllowedCountriesUseCase()
        val allCountries: List<GeoCountry> = getAllCountriesUseCase()

        val allCountriesFilterAllowed: List<GeoCountry> = allCountries.filter { geoCountry ->
            allowedCountries.any { allowedCountry -> allowedCountry.id == geoCountry.id }
        }

        val allCountriesFilterAllowedAndWithTop: List<GeoCountry> =
            allCountriesFilterAllowed.map { geoCountry ->
                geoCountry.copy(top = allowedCountries.find { allowedCountry ->
                    geoCountry.id == allowedCountry.id
                }?.top ?: false)
            }

        val mapValueIndex: Map<String, Int> =
            allowedCountries.map { it.id }.withIndex().associate { indexedValue ->
                indexedValue.value to indexedValue.index
            }

        val allCountriesFilterAllowedAndWithTopSortedBuIndex: List<GeoCountry> =
            allCountriesFilterAllowedAndWithTop.sortedBy { geoCountry ->
                mapValueIndex[geoCountry.id] // All список отсортировали по позициям allowed
            }

        val geoCountryModelMapList: List<GeoCountryModel> =
            allCountriesFilterAllowedAndWithTopSortedBuIndex.map { geoCountry ->
                geoCountry.toGeoCountryModel(service)
            }

        val filterBlackWhiteList =
            when {
                whiteList.isNotEmpty() -> {
                    geoCountryModelMapList.filter { geoCountryModel ->
                        whiteList.contains(geoCountryModel.countryCode)
                    }
                }

                blackList.isNotEmpty() -> {
                        geoCountryModelMapList.filter { geoCountryModel ->
                        !blackList.contains(geoCountryModel.countryCode)
                    }
                }

                else -> geoCountryModelMapList
            }

        val registrationChoiceList = filterBlackWhiteList.map { geoCountryModel ->
            toRegistrationChoice(
                geoCountryModel = geoCountryModel,
                type = type,
                selectedCountryId = selectedCountryId
            )
        }

        //Log.d("JunitScenario", "allCountriesFilterAllowed - $allCountriesFilterAllowed")
        //Log.d(
        //    "JunitScenario",
        //    "allCountriesFilterAllowedAndWithTop - $allCountriesFilterAllowedAndWithTop"
        //)
        //Log.d("JunitScenario", "mapToIndex - $mapValueIndex")
        //Log.d(
        //    "JunitScenario",
        //    "allCountriesFilterAllowedAndWithTopSortedBuIndex - $allCountriesFilterAllowedAndWithTopSortedBuIndex"
        //)

        //Log.d("JunitScenario", "whiteList - $whiteList")
        //Log.d("JunitScenario", "blackList - $blackList")
        //Log.d("JunitScenario", "filterBlackWhiteList - $filterBlackWhiteList")
        //Log.d("JunitScenario", "registrationChoiceList - $registrationChoiceList")

        return registrationChoiceList
    }
}

private fun toRegistrationChoice(
    geoCountryModel: GeoCountryModel,
    type: RegistrationChoiceType,
    selectedCountryId: String,
): RegistrationChoice =
    RegistrationChoice(
        id = geoCountryModel.id,
        text = when (type) {
            RegistrationChoiceType.PHONE -> "${geoCountryModel.countryCode} ${geoCountryModel.name}"
            RegistrationChoiceType.COUNTRY -> geoCountryModel.name
        },
        isChoice = selectedCountryId == geoCountryModel.id,
        type = type,
        top = geoCountryModel.top, // TODO не забыть проверить на что влияет и можно ли убрать
        image = "",
    )