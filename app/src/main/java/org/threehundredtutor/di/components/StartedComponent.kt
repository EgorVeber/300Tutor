package org.threehundredtutor.di.components

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.PrefsSettingsDagger
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.presentation.StartedFragment

/**
 * Отдельная компонента, не SubComponent, свой модуль и со своей scope*
 * Нужно чтоб в компоненте был свой инстанс фабрики вью модели
 */

@Component()
@ScreenScope
interface StartedComponent {

    fun inject(startedFragment: StartedFragment)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getPrefsSettingsDagger(prefsSettingsDagger: PrefsSettingsDagger): Builder
        fun getStartedComponentBuilder(): StartedComponent
    }

    companion object {
        fun createStartedComponent(): StartedComponent =
            DaggerStartedComponent
                .builder()
                .getPrefsSettingsDagger(DiSetHelper.appComponent.getPrefsSettingsDagger())
                .getStartedComponentBuilder()
    }
}