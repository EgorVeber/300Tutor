package ru.gb.veber.newsapi.core.utils

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.threehundredtutor.junit.aff.AvailableCreatePromoCodeModel
import org.threehundredtutor.junit.aff.CompanyListingModel
import org.threehundredtutor.junit.aff.CurrencyListingModel
import org.threehundredtutor.junit.aff.GetCompanyListingUseCase
import org.threehundredtutor.junit.aff.GetCurrencyListingUseCase
import org.threehundredtutor.junit.aff.GetDefaultCompanyUseCase
import org.threehundredtutor.junit.aff.GetDefaultCurrencyUseCase
import org.threehundredtutor.junit.aff.GetDefaultSiteUseCase
import org.threehundredtutor.junit.aff.GetGeneralPromoCodeUseCase
import org.threehundredtutor.junit.aff.GetSiteListingUseCase
import org.threehundredtutor.junit.aff.SiteListingModel

class GetGeneralPromoCodeUseCaseTest {

    @RelaxedMockK
    lateinit var getDefaultSiteUseCase: GetDefaultSiteUseCase

    @RelaxedMockK
    lateinit var getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase

    @RelaxedMockK
    lateinit var getDefaultCompanyUseCase: GetDefaultCompanyUseCase

    @RelaxedMockK
    lateinit var getSiteListingUseCase: GetSiteListingUseCase

    @RelaxedMockK
    lateinit var getCurrencyListingUseCase: GetCurrencyListingUseCase

    @RelaxedMockK
    lateinit var getCompanyListingUseCase: GetCompanyListingUseCase

    @InjectMockKs
    private lateinit var getGeneralPromoCodeUseCase: GetGeneralPromoCodeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN all useCases return correct data EXPECT availableCreatePromoCode true`() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings
        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns company4Currency12

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency12,
            companyModel = company4Currency12,
            siteModel = site595,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN all default useCases returns empty EXPECTED firstItems `() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns emptySite
        every { getDefaultCurrencyUseCase() } returns emptyCurrency
        every { getDefaultCompanyUseCase() } returns emptyCompany

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency11FirstItem,
            siteModel = site716FirstItem,
            companyModel = company2Currency11,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN listings useCases return empty list  EXPECTED firstItems `() {
        every { getCurrencyListingUseCase() } returns listOf()
        every { getSiteListingUseCase() } returns listOf()
        every { getCompanyListingUseCase() } returns listOf()

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns company4Currency12

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = emptyCurrency,
            siteModel = emptySite,
            companyModel = emptyCompany,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN CurrencyListing empty EXPECTED availableCreatePromoCode false and emptyCompany `() {
        every { getSiteListingUseCase() } returns siteListings
        every { getCurrencyListingUseCase() } returns listOf()
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency15
        every { getDefaultCompanyUseCase() } returns company2Currency11

        val expected = AvailableCreatePromoCodeModel(
            siteModel = site595,
            currencyModel = emptyCurrency,
            companyModel = emptyCompany,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN SiteListing empty EXPECTED availableCreatePromoCode false `() {
        every { getSiteListingUseCase() } returns listOf()
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency15
        every { getDefaultCompanyUseCase() } returns company5Currency15

        val expected = AvailableCreatePromoCodeModel(
            siteModel = emptySite,
            currencyModel = currency15,
            companyModel = company5Currency15,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN companyListings empty EXPECTED availableCreatePromoCode false `() {
        every { getSiteListingUseCase() } returns siteListings
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getCompanyListingUseCase() } returns listOf()

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency15
        every { getDefaultCompanyUseCase() } returns company5Currency15

        val expected = AvailableCreatePromoCodeModel(
            siteModel = site595,
            currencyModel = currency15,
            companyModel = emptyCompany,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultSiteUseCase returns empty EXPECTED firstItem site `() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings
        every { getDefaultSiteUseCase() } returns emptySite
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns company4Currency12

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency12,
            companyModel = company4Currency12,
            siteModel = site716FirstItem,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultSiteUseCase returns site none listings EXPECTED firstItem `() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings
        every { getDefaultSiteUseCase() } returns siteNotListing
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns company4Currency12

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency12,
            companyModel = company4Currency12,
            siteModel = site716FirstItem,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultCurrencyUseCase returns emptyCurrency EXPECTED firstItem currency and firstItem company`() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns emptyCurrency
        every { getDefaultCompanyUseCase() } returns company4Currency12

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency11FirstItem,
            companyModel = company2Currency11,
            siteModel = site595,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultCurrencyUseCase returns currencyNotListing EXPECTED firstItem currency and firstItem company`() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currencyNotListing
        every { getDefaultCompanyUseCase() } returns emptyCompany

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency11FirstItem,
            companyModel = company2Currency11,
            siteModel = site595,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultCurrencyUseCase returns currencyNotListing and availableCompany false EXPECTED availableCreatePromoCode false `() {
        every { getSiteListingUseCase() } returns siteListings
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getCompanyListingUseCase() } returns companyListings.filter { it.currencyId != currency11FirstItem.currencyId }

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currencyNotListing
        every { getDefaultCompanyUseCase() } returns companyNotListingNotCurrency

        val expected = AvailableCreatePromoCodeModel(
            siteModel = site595,
            currencyModel = currency11FirstItem,
            companyModel = emptyCompany,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultCurrencyUseCase returns currency15 and availableCompany false EXPECTED availableCreatePromoCode false `() {
        every { getSiteListingUseCase() } returns siteListings
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getCompanyListingUseCase() } returns companyListings.filter { it.currencyId != currency15.currencyId }

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency15
        every { getDefaultCompanyUseCase() } returns companyNotListingNotCurrency

        val expected = AvailableCreatePromoCodeModel(
            siteModel = site595,
            currencyModel = currency15,
            companyModel = emptyCompany,
            availableCreatePromoCode = false
        )

        val actual = getGeneralPromoCodeUseCase()
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getDefaultCompanyUseCase returns emptyCompany EXPECTED firstItem company`() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns emptyCompany

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency12,
            siteModel = site595,
            companyModel = company1Currency12FirstItem,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `WWHEN getDefaultCompanyUseCase returns company not listings EXPECTED firstItem company`() {
        every { getCurrencyListingUseCase() } returns currencyListing
        every { getSiteListingUseCase() } returns siteListings
        every { getCompanyListingUseCase() } returns companyListings

        every { getDefaultSiteUseCase() } returns site595
        every { getDefaultCurrencyUseCase() } returns currency12
        every { getDefaultCompanyUseCase() } returns companyNotListing

        val expected = AvailableCreatePromoCodeModel(
            currencyModel = currency12,
            siteModel = site595,
            companyModel = company1Currency12FirstItem,
            availableCreatePromoCode = true
        )

        val actual = getGeneralPromoCodeUseCase()

        assertEquals(expected, actual)
    }

    @After
    fun clear() {
        unmockkAll()
    }

    companion object {
        val currency11FirstItem = CurrencyListingModel(currencyId = 11, currency = "RUB")
        val currency12 = CurrencyListingModel(currencyId = 12, currency = "USD")
        private val currency15 = CurrencyListingModel(currencyId = 15, currency = "Bitkoin")
        val currencyListing = listOf(currency11FirstItem, currency12, currency15)
        val currencyNotListing = CurrencyListingModel(currencyId = 12312, currency = "NotCurrency")
        val emptyCurrency = CurrencyListingModel.EMPTY

        val site716FirstItem = SiteListingModel(siteId = 716, site = "https://mockk.716")
        private val site321 = SiteListingModel(siteId = 321, site = "https://mockk.321")
        private val site872 = SiteListingModel(siteId = 872, site = "https://mockk.872")
        private val site124 = SiteListingModel(siteId = 124, site = "https://mockk.124")
        val site595 = SiteListingModel(siteId = 595, site = "https://mockk.595")
        val siteListings = listOf(site716FirstItem, site321, site872, site124, site595)
        val emptySite = SiteListingModel.EMPTY
        val siteNotListing = SiteListingModel(siteId = 5952, site = "https:/sdadas/mocksa")


        val company1Currency12FirstItem =
            CompanyListingModel(companyId = 1, company = "Android1", currencyId = 12)
        val company2Currency11 =
            CompanyListingModel(companyId = 2, company = "Android2", currencyId = 11)
        private val company3Currency12 = CompanyListingModel(
            companyId = 3, company = "Android3", currencyId = 12
        )
        val company4Currency12 =
            CompanyListingModel(companyId = 4, company = "Android4", currencyId = 12)
        private val company5Currency15 = CompanyListingModel(
            companyId = 5, company = "Android5", currencyId = 15
        )
        val companyListings = listOf(
            company1Currency12FirstItem,
            company2Currency11,
            company3Currency12,
            company4Currency12,
            company5Currency15,
        )
        val emptyCompany = CompanyListingModel.EMPTY
        val companyNotListing =
            CompanyListingModel(companyId = 2312312, company = "Android777", currencyId = 12)
        val companyNotListingNotCurrency =
            CompanyListingModel(companyId = 23129102, company = "Android888", currencyId = 777)
    }
}
