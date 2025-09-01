package uz.shoxrux.tmdb.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.tmdb.R
import javax.inject.Inject

class LocaleProviderImpl @Inject constructor(
    @param: ApplicationContext private val context: Context
) : LocaleProvider {
    override fun getLanguage(): String {
        return context.getString(R.string.language)
    }

}