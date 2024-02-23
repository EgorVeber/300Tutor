package org.threehundredtutor.junit.aff

class GetSiteListingUseCase {
    operator fun invoke() = listOf<SiteListingModel>()
}

data class SiteListingModel(
    val siteId: Long,
    val site: String,
) {

    companion object {
        val EMPTY = SiteListingModel(siteId = -1, site = "")
    }
}
