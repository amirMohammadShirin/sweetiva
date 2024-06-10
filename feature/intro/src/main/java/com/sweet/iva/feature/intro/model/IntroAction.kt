package com.sweet.iva.feature.intro.model

import com.sweet.iva.core.ui.model.IAction

/**
 * Created by aShirin on 6/8/2024.
 */
sealed interface IntroAction : IAction {

    data object EntryButtonClicked : IntroAction

}