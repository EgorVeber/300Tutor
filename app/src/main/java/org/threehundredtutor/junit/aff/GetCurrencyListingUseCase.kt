package org.threehundredtutor.junit.aff

class GetCurrencyListingUseCase {
    operator fun invoke() = listOf<CurrencyListingModel>()
}

data class CurrencyListingModel(
    val currencyId: Long,
    val currency: String,
) {

    companion object {
        val EMPTY = CurrencyListingModel(currencyId = -1, currency = "")
    }
}