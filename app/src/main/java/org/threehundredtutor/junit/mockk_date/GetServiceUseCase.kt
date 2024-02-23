package org.threehundredtutor.junit.mockk_date

import javax.inject.Inject

class GetServiceUseCase @Inject constructor() {
    operator fun invoke() = "service"
}
