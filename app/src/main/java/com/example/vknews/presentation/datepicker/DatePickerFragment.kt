package com.example.vknews.presentation.datepicker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.vknews.R
import com.example.vknews.databinding.FragmentDatepickerBinding
import com.example.vknews.presentation.core.baseMoxyPresenter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatDialogFragment
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Provider

private const val ARGUMENT_DATE = "ARGUMENT_DATE"

class DatePickerFragment : MvpAppCompatDialogFragment(), DatePickerView, HasAndroidInjector {

    companion object {
        fun newInstance(date: LocalDateTime) = DatePickerFragment()
            .apply {
                arguments = bundleOf(ARGUMENT_DATE to date)
            }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenterProvider: Provider<DatePickerPresenter>

    protected val presenter by baseMoxyPresenter(::presenterProvider::class.java.name) { presenterProvider.get() }

    private val binding by lazy { FragmentDatepickerBinding.inflate(requireParentFragment().layoutInflater) }

    private val date by lazy { arguments?.get(ARGUMENT_DATE) as LocalDateTime }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.choose_date)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }
}