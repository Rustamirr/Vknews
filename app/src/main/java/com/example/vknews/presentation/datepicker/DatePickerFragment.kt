package com.example.vknews.presentation.datepicker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.vknews.databinding.FragmentDatepickerBinding
import com.example.vknews.presentation.core.baseMoxyPresenter
import com.example.vknews.presentation.news.NewsViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatDialogFragment
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Provider

private const val ARGUMENT_DATE = "ARGUMENT_DATE"
private const val ARGUMENT_DATE_TYPE = "ARGUMENT_DATE_TYPE"

class DatePickerFragment : MvpAppCompatDialogFragment(), DatePickerView, HasAndroidInjector {

    companion object {
        fun newInstance(datePickerDate: DatePickerDate) = DatePickerFragment()
            .apply {
                arguments = bundleOf(
                    ARGUMENT_DATE to datePickerDate.date,
                    ARGUMENT_DATE_TYPE to datePickerDate.dateType
                )
            }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenterProvider: Provider<DatePickerPresenter>

    protected val presenter by baseMoxyPresenter(::presenterProvider::class.java.name) { presenterProvider.get() }

    private val binding by lazy { FragmentDatepickerBinding.inflate(requireParentFragment().layoutInflater) }

    private val date by lazy { arguments?.get(ARGUMENT_DATE) as LocalDate }
    private val dateType by lazy { arguments?.get(ARGUMENT_DATE_TYPE) as DatePickerDateType }

    private val newsViewModel: NewsViewModel by viewModels({ requireParentFragment() })

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .apply { initDatePicker() }
            .setPositiveButton(android.R.string.ok) { _, _ -> onPositiveButtonClick() }
            .create()
    }

    private fun initDatePicker() {
        binding.datePicker.init(
            date.year, date.monthValue - 1, date.dayOfMonth, null
        )
    }

    private fun onPositiveButtonClick() {
        with(binding) {
            newsViewModel.onDateChosen(
                DatePickerDate(
                    LocalDate.of(datePicker.year, datePicker.month + 1, datePicker.dayOfMonth),
                    dateType
                )
            )
        }
    }
}