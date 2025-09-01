package uz.shoxrux.core.providers

import androidx.annotation.StringRes

interface LocaleProvider {

    fun getLanguage(@StringRes key: Int): String

}