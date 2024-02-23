package org.threehundredtutor.junit.aff

data class AvailableCreatePromoCodeModel(
    val currencyModel: CurrencyListingModel,
    val siteModel: SiteListingModel,
    val companyModel: CompanyListingModel,
    val availableCreatePromoCode: Boolean,
)
