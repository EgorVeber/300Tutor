package org.threehundredtutor.presentation.course

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.base.BaseViewModel
import org.threehundredtutor.ui_common.util_class.BundleString
import org.threehundredtutor.ui_core.databinding.WebViewFragmentBinding

class WebViewFragment : BaseFragment(UiCoreLayout.web_view_fragment) {

    override val bottomMenuVisible: Boolean = false

    private lateinit var binding: WebViewFragmentBinding

    override var customHandlerBackStack: Boolean = true

    override val viewModel: BaseViewModel by viewModels()

    private val link by BundleString(LINK_KEY, EMPTY_STRING)
    private val schoolName by BundleString(SCHOOL_KEY, EMPTY_STRING)
    private val siteUrl by BundleString(SITE_URL_KEY, EMPTY_STRING)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = WebViewFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            findNavController().popBackStack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.toolbarWebView.setTitle(schoolName)
        binding.toolbarWebView.setSubtitle(siteUrl)
        if (savedInstanceState != null) {
            binding.webView.restoreState(savedInstanceState)
            binding.webView.settings.javaScriptEnabled = true
        } else {
            with(binding.webView) {
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                clearHistory()
                clearCache(true)
            }
            binding.webView.loadUrl(link)
        }

        binding.toolbarWebView.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val LINK_KEY = "COURSE_LINK_KEY"
        const val SITE_URL_KEY = "SITE_URL_KEY"
        const val SCHOOL_KEY = "SCHOOL_KEY"
    }
}