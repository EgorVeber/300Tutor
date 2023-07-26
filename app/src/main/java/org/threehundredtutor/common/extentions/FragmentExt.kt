package org.threehundredtutor.common.extentions

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes resId: Int) = findNavController().navigate(resId)

fun Fragment.showMessage(text: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, text, length).show()