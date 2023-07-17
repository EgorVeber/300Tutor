package org.threehundredtutor.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate","Created screen ${this::class.java.name}" )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView(savedInstanceState)
        onObserveData()
    }

    protected open fun onInject() {}

    protected open fun onInitView(savedInstanceState: Bundle?) {}

    protected open fun onObserveData(){}

}
