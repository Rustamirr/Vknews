package com.example.vknews.presentation.navigation

import androidx.fragment.app.FragmentManager
import com.example.vknews.R
import com.example.vknews.presentation.mainactivity.MainActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class NavigatorImpl
@Inject constructor(
    activity: MainActivity
) : SupportAppNavigator(activity, R.id.container) {

    override fun fragmentForward(command: Forward) {
        with(command.screen) {
            when (this) {
                is Screen.Dialog -> {
                    fragment.show(fragmentManager.requireChildFragmentManager, this.tag)
                }
                else -> super.fragmentForward(command)
            }
        }
    }

    private val FragmentManager.requireChildFragmentManager: FragmentManager
        get() = requireNotNull(findFragmentById(containerId)).childFragmentManager
}
