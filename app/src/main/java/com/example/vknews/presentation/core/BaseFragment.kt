package com.example.vknews.presentation.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseFragment<VB : ViewBinding, S, P : BasePresenter<S, *, *>> :
        MvpAppCompatFragment(), BaseView, HasAndroidInjector {

    private var _binding: VB? = null

    protected val binding: VB
        get() = requireNotNull(_binding) {
            "This property is only valid between onCreateView and onDestroyView"
        }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenterProvider: Provider<P>

    protected val presenter: P by baseMoxyPresenter(::presenterProvider::class.java.name) { presenterProvider.get() }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.createBinding(container).also { _binding = it }.root

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun LayoutInflater.createBinding(container: ViewGroup?): VB
}