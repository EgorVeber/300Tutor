import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.presentation.authorization.AuthorizationFragment

object AuthScreen : KScreen<AuthScreen>() {

    override val layoutId: Int = UiCoreLayout.authorization_fragment
    override val viewClass: Class<*> = AuthorizationFragment::class.java
    val flakyButton = KButton { withId(R.id.signInButton) }
}
