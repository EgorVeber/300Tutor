package org.threehundredtutor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

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