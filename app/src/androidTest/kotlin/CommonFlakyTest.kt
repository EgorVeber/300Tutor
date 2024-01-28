import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.threehundredtutor.presentation.starter.StartedActivity

class CommonFlakyTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<StartedActivity>()

    @Test
    fun test() = run {
        step("Open Scroll View Stub Screen") {
            AuthScreen {
                flakyButton {
                    click()
                }
            }
        }

        Thread.sleep(5000)
    }
}
