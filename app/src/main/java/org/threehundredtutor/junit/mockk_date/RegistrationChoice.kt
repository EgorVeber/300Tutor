package org.threehundredtutor.junit.mockk_date

data class RegistrationChoice(
    private val id: String,
    private val text: String,
    private val isChoice: Boolean,
    private val type: RegistrationChoiceType,
    private val top: Boolean,
    private val image: String,
)