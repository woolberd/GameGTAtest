package com.example.gamegtatest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewManager
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val context: Context,
    private var parentView: ViewGroup? = null
) {

    protected var _mViewUI: VB? = null
    protected val mViewUI: VB get() = _mViewUI!!

    var isShow: Boolean = false

    protected open fun onCreateView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _mViewUI = inflate(inflater)
        parentView?.addView(mViewUI.root)
        isShow = true
    }

    protected abstract fun inflate(inflater: LayoutInflater): VB

    protected open fun onDestroy() {
        (mViewUI.root.parent as ViewManager).removeView(mViewUI.root)
        parentView = null
        isShow = false
        _mViewUI = null
    }

    open fun show() {}
    open fun hide() {}

    abstract fun closeCurrentFragment()

}