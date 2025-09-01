package uz.shoxrux.tmdb.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.shoxrux.core.providers.LocaleProvider
import javax.inject.Inject

class LocaleProviderImpl @Inject constructor(
    @param: ApplicationContext private val context: Context
) : LocaleProvider {

    override fun getLanguage(key: Int): String {
        return context.getString(key)
    }

}