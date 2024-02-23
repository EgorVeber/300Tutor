package org.threehundredtutor.junit.aff

class GetCompanyListingUseCase {
    operator fun invoke() = listOf<CompanyListingModel>()
}


data class CompanyListingModel(
    val companyId: Long,
    val company: String,
    val currencyId: Long,
) {

    companion object {
        val EMPTY = CompanyListingModel(companyId = -1, company = "", currencyId = -1)
    }
}


class GetDefaultCompanyUseCase {
    operator fun invoke(): CompanyListingModel = CompanyListingModel.EMPTY
}

class GetDefaultCurrencyUseCase {
    operator fun invoke(): CurrencyListingModel = CurrencyListingModel.EMPTY
}

class GetDefaultSiteUseCase {
    operator fun invoke(): SiteListingModel = SiteListingModel.EMPTY
}