package org.threehundredtutor.di.account

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.PrefsCookie
import org.threehundredtutor.common.utils.PrefsSettingsDagger
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory

@Component(modules = [AccountModule::class])
@ScreenScope
interface AccountComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getPrefsSettingsDagger(prefsSettingsDagger: PrefsSettingsDagger): Builder

        @BindsInstance
        fun getPrefsCookie(prefsCookie: PrefsCookie): Builder

        fun getAccountComponentBuilder(): AccountComponent
    }

    companion object {
        fun createAccountComponent(): AccountComponent =
            DaggerAccountComponent
                .builder()
                .getBuilder(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getPrefsSettingsDagger(DiSetHelper.appComponent.getPrefsSettingsDagger())
                .getPrefsCookie(DiSetHelper.appComponent.getPrefsCookie())
                .getAccountComponentBuilder()
    }
}