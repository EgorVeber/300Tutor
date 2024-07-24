package org.threehundredtutor.ui_common.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AttrRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threehundredtutor.ui_common.UiCoreAttr
import org.threehundredtutor.ui_common.UiCoreStrings
import org.threehundredtutor.ui_common.util.getUrlYoutube
import org.threehundredtutor.ui_core.TutorSnackbar

fun Fragment.getColorAttr(@AttrRes attrRes: Int, needResId: Boolean = true): Int {
    val typedValue = TypedValue()
    context?.theme?.resolveAttribute(attrRes, typedValue, true)
    return if (needResId) typedValue.resourceId else typedValue.data
}

fun Fragment.showMessage(text: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, text, length).show()

fun Fragment.addBackPressedCallback(
    action: () -> Unit
) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action.invoke()
            }
        })
}

fun Fragment.launchDelay(delay: Long, action: () -> Unit) {
    lifecycleScope.launch {
        delay(delay)
        action()
    }
}

fun Fragment.applyWindowColor(
    @AttrRes statusBarColorAttr: Int,
    @AttrRes navigationBarColorAttr: Int? = null
) {
    requireActivity().window.apply {
        statusBarColor = getColorAttr(statusBarColorAttr, false)
        navigationBarColor = getColorAttr(navigationBarColorAttr ?: statusBarColorAttr, false)
    }
}

fun Fragment.addBackPressedDelayCallback(delay: Long = 2000L, count: Int = 1) {
    var backStack = count
    addBackPressedCallback {
        launchDelay(delay) { backStack = count }
        if (backStack != 0) showMessage(getString(UiCoreStrings.press_again))
        else requireActivity().finish()
        backStack--
    }
}

fun Fragment.dropWindowColor() {
    requireActivity().window.apply {
        statusBarColor = getColorAttr(UiCoreAttr.background, false)
        navigationBarColor = getColorAttr(UiCoreAttr.contentBackground, false)
    }
}

fun Fragment.showSnack(
    view: View? = null,
    title: String,
    backgroundColor: Int? = null,
    description: String? = null,
    buttonText: String? = null,
    length: Int = Snackbar.LENGTH_LONG,
    buttonClick: (() -> Unit)? = null,
    imageResId: Int? = null
) {
    TutorSnackbar.make(
        view = view ?: this.requireView(),
        backgroundColor = backgroundColor,
        title = title,
        message = description,
        imageResId = imageResId,
        buttonText = buttonText,
        length = length,
        buttonClick = buttonClick
    ).show()
}

fun Fragment.openYoutubeLink(link: String) {
    val (appUri, browserUri) = link.getUrlYoutube()
    try {
        startActivity(Intent(Intent.ACTION_VIEW, appUri))
    } catch (ex: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, browserUri))
    }
}

