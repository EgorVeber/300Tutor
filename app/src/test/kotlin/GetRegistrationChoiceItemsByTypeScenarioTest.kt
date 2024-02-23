package ru.gb.veber.newsapi.core.utils

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.threehundredtutor.junit.GetRegistrationChoiceItemsByTypeScenario
import org.threehundredtutor.junit.mockk_date.AllowedCountry
import org.threehundredtutor.junit.mockk_date.GeoCountry
import org.threehundredtutor.junit.mockk_date.GetAllCountriesUseCase
import org.threehundredtutor.junit.mockk_date.GetAllowedCountriesUseCase
import org.threehundredtutor.junit.mockk_date.GetServiceUseCase
import org.threehundredtutor.junit.mockk_date.RegistrationChoice
import org.threehundredtutor.junit.mockk_date.RegistrationChoiceType

class GetRegistrationChoiceItemsByTypeScenarioTest {
    @RelaxedMockK
    lateinit var getAllCountriesUseCase: GetAllCountriesUseCase

    @RelaxedMockK
    lateinit var getAllowedCountriesUseCase: GetAllowedCountriesUseCase

    @RelaxedMockK
    lateinit var getServiceUseCase: GetServiceUseCase

    @InjectMockKs
    private lateinit var scenario: GetRegistrationChoiceItemsByTypeScenario


    private val service: String = "service"
    private val type = RegistrationChoiceType.COUNTRY
    private val selectedCountryId = "br"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { getServiceUseCase() } returns service
    }

    @Test
    fun `WHEN scenario call EXPECTED call UseCases`() {
        //Prepare
        val expected = expectedRegistrationChoiceList
        every { getAllowedCountriesUseCase() } returns getAllowedCountries
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN allowedCountriesList isEmpty EXPECTED empty registrationChoiceList`() {
        //Prepare
        val expected = listOf<RegistrationChoice>()
        every { getAllowedCountriesUseCase() } returns listOf()
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN allCountriesList isEmpty expected EXPECTED registrationChoiceList`() {
        //Prepare
        val expected = listOf<RegistrationChoice>()
        every { getAllowedCountriesUseCase() } returns getAllowedCountries
        every { getAllCountriesUseCase() } returns listOf()

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN allCountryList not equal allowedCountries EXPECTED filtered allCountryList`() {
        //Prepare
        val expected = expectedRegistrationChoiceList.slice(0..1) //expectedRegistrationChoiceListNotArgentina
        every { getAllowedCountriesUseCase() } returns getAllowedCountriesNotArgentina
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN call scenario EXPECTED sortedBy allowedCountriesList with position`() {
        //Prepare
        val expected = expectedRegistrationChoiceListArgentinaFirstItem
        every { getAllowedCountriesUseCase() } returns getAllowedCountriesArgentinaFirstItem
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN whiteList no one allCountryList item EXPECTED emptyList`() {
        //Prepare
        val expected = listOf<RegistrationChoice>()
        every { getAllowedCountriesUseCase() } returns getAllowedCountries
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = getWhiteList,
            blackList = listOf()
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @Test
    fun `WHEN whiteList empty and blacklist is not empty EXPECTED filtered list by blacklist `() {
        //Prepare
        val expected = expectedRegistrationChoiceListByBlackList
        every { getAllowedCountriesUseCase() } returns getAllowedCountries
        every { getAllCountriesUseCase() } returns getAllCountries

        //Do
        val actual = scenario.invoke(
            selectedCountryId = selectedCountryId,
            type = type,
            whiteList = listOf(),
            blackList = getBlackList
        )

        //Check
        assertEquals(expected, actual)
        verify(exactly = 1) {
            getAllCountriesUseCase()
            getAllowedCountriesUseCase()
            getServiceUseCase()
        }
    }

    @After
    fun clear() {
        unmockkAll()
    }

    companion object {
        val expectedRegistrationChoiceList = listOf(
            RegistrationChoice(
                id = "br",
                text = "Brazil",
                isChoice = true,
                type = RegistrationChoiceType.COUNTRY,
                top = true,
                image = "",
            ),
            RegistrationChoice(
                id = "bg",
                text = "Bulgaria",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            ),
            RegistrationChoice(
                id = "ar",
                text = "Argentina",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            ),
        )

        val expectedRegistrationChoiceListNotArgentina = listOf(
            RegistrationChoice(
                id = "br",
                text = "Brazil",
                isChoice = true,
                type = RegistrationChoiceType.COUNTRY,
                top = true,
                image = "",
            ),
            RegistrationChoice(
                id = "bg",
                text = "Bulgaria",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            )
        )

        val expectedRegistrationChoiceListArgentinaFirstItem = listOf(
            RegistrationChoice(
                id = "ar",
                text = "Argentina",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            ),
            RegistrationChoice(
                id = "br",
                text = "Brazil",
                isChoice = true,
                type = RegistrationChoiceType.COUNTRY,
                top = true,
                image = "",
            ),
            RegistrationChoice(
                id = "bg",
                text = "Bulgaria",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            ),
        )

        val expectedRegistrationChoiceListByBlackList = listOf(
            RegistrationChoice(
                id = "ar",
                text = "Argentina",
                isChoice = false,
                type = RegistrationChoiceType.COUNTRY,
                top = false,
                image = "",
            ),
        )

        val getAllCountries = listOf(
            GeoCountry(name = "Brazil", id = "br", countryCode = "11", top = false),
            GeoCountry(name = "Bulgaria", id = "bg", countryCode = "22", top = false),
            GeoCountry(name = "Argentina", id = "ar", countryCode = "33", top = false),
        )

        val getAllowedCountries = listOf(
            AllowedCountry(name = "Brazil", id = "br", countryCode = "11", top = true),
            AllowedCountry(name = "Bulgaria", id = "bg", countryCode = "22", top = false),
            AllowedCountry(name = "Argentina", id = "ar", countryCode = "33", top = false),
        )

        val getAllowedCountriesArgentinaFirstItem = listOf(
            AllowedCountry(name = "Argentina", id = "ar", countryCode = "33", top = false),
            AllowedCountry(name = "Brazil", id = "br", countryCode = "11", top = true),
            AllowedCountry(name = "Bulgaria", id = "bg", countryCode = "22", top = false),
        )

        val getAllowedCountriesNotArgentina = listOf(
            AllowedCountry(name = "Brazil", id = "br", countryCode = "11", top = true),
            AllowedCountry(name = "Bulgaria", id = "bg", countryCode = "22", top = false),
        )

        val getWhiteList = listOf(
            "77",
        )

        val getBlackList = listOf(
            "11",
            "22",
        )
    }
}
