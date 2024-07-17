package org.threehundredtutor.di.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Фабрика умеет вызывать провайдер и по классу(типу) вью модели получать инстанс вью модели уже со всеми инжектами тк provider даггеровский*
 * Нам нужно чтобы даггер создавал инстансы вью можелей иначе не сможем запихнуть зависимости, а ЖЦ контролировался уже фрагменто*
 * Создается модель с нуля или уже созданная даггер не должен решать  *
 */

class ViewModelMapFactory @Inject constructor(
    // Класс вью модели и провайдер вью модели, только возвращается
    private val viewModelMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel> = viewModelMap[modelClass]
            ?: throw IllegalArgumentException("Нету провайдера, фактори для вью модели такова класса $modelClass")
        return viewModelProvider.get() as T
    }
}

class Factory<T : ViewModel>(
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val create: (stateHandle: SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return create.invoke(handle) as T
    }
}

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    Factory(this, create)
}

/**
 * Позволяет сделать inject в map вроде *
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelInjectMapKey(val value: KClass<out ViewModel>)