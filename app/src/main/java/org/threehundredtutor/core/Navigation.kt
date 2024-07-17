package org.threehundredtutor.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes resId: Int, bundle: Bundle? = null) =
    findNavController().navigate(resId, bundle)