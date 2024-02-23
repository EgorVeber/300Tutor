package org.threehundredtutor.junit.aff

import javax.inject.Inject

class GetGeneralPromoCodeUseCase @Inject constructor(
    private val getCompanyListingUseCase: GetCompanyListingUseCase,
    private val getCurrencyListingUseCase: GetCurrencyListingUseCase,
    private val getSiteListingUseCase: GetSiteListingUseCase,
    private val getDefaultCompanyUseCase: GetDefaultCompanyUseCase,
    private val getDefaultSiteUseCase: GetDefaultSiteUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
) {
    operator fun invoke(): AvailableCreatePromoCodeModel {
        val defaultSite = getDefaultSiteUseCase()
        val defaultCurrency = getDefaultCurrencyUseCase()
        val defaultCompany = getDefaultCompanyUseCase()
        val companyList = getCompanyListingUseCase()
        val currencyList = getCurrencyListingUseCase()
        val siteList = getSiteListingUseCase()

        val availableDefaultCurrency = currencyList.contains(defaultCurrency)
        val availableDefaultSite = siteList.contains(defaultSite)

        val siteFirstItemOrEmpty = siteList.firstOrNull() ?: SiteListingModel.EMPTY
        val currencyFirstItemOrEmpty =
            currencyList.firstOrNull() ?: CurrencyListingModel.EMPTY

        val currentCurrencyId =
            if (availableDefaultCurrency) defaultCurrency.currencyId else currencyFirstItemOrEmpty.currencyId
        val availableDefaultCompany =
            companyList.contains(defaultCompany) && defaultCompany.currencyId == currentCurrencyId

        val companyFirstItemOrEmpty = companyList.firstOrNull { it.currencyId == currentCurrencyId }
            ?: CompanyListingModel.EMPTY

        val availableCreatePromoCode =
            (availableDefaultCurrency || currencyFirstItemOrEmpty != CurrencyListingModel.EMPTY) &&
                    (availableDefaultSite || siteFirstItemOrEmpty != SiteListingModel.EMPTY) &&
                    (availableDefaultCompany || companyFirstItemOrEmpty != CompanyListingModel.EMPTY)

        return AvailableCreatePromoCodeModel(
            currencyModel = if (availableDefaultCurrency) defaultCurrency else currencyFirstItemOrEmpty,
            siteModel = if (availableDefaultSite) defaultSite else siteFirstItemOrEmpty,
            companyModel = if (availableDefaultCompany) defaultCompany else companyFirstItemOrEmpty,
            availableCreatePromoCode = availableCreatePromoCode,
        )
    }
}

//  Log.d("PromoCodeUseCase", "Default")
//  Log.d("PromoCodeUseCase", defaultSite.toString())
//  Log.d("PromoCodeUseCase", defaultCurrency.toString())
//  Log.d("PromoCodeUseCase", defaultCompany.toString())
//
//  Log.d("PromoCodeUseCase", "Listings")
//  Log.d("PromoCodeUseCase", companyList.toString())
//  Log.d("PromoCodeUseCase", currencyList.toString())
//  Log.d("PromoCodeUseCase", siteList.toString())
//
//  Log.d("PromoCodeUseCase", "FirstItems")
//  Log.d("PromoCodeUseCase", companyFirstItemOrEmpty.toString())
//  Log.d("PromoCodeUseCase", currencyFirstItemOrEmpty.toString())
//  Log.d("PromoCodeUseCase", siteFirstItemOrEmpty.toString())
//
//  Log.d("PromoCodeUseCase", "AvailableDefault")
//  Log.d("PromoCodeUseCase", "availableCompany = $availableCompany")
//  Log.d("PromoCodeUseCase", "availableCurrency = $availableCurrency")
//  Log.d("PromoCodeUseCase", "availableSite = $availableSite")
//
//  Log.d("PromoCodeUseCase", "result = $result")