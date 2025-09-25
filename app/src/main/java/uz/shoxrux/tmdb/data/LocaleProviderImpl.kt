package uz.shoxrux.tmdb.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.shoxrux.core.annotations.ApplicationScope
import uz.shoxrux.core.providers.LocalCacheProvider
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.tmdb.R
import javax.inject.Inject

class LocaleProviderImpl @Inject constructor(
    @param: ApplicationScope private val scope: CoroutineScope,
    context: Context,
    private val localCacheProvider: LocalCacheProvider
) : LocaleProvider {

    @Volatile
    private var language: String = context.getString(R.string.language)

    init {
        scope.launch {
            localCacheProvider.getLanguage().collect {
                language = it
            }
        }
    }

    override fun getLanguage(): String = language
}