package com.sweet.iva.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataStore @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "iva_datastore")

    val datastore: DataStore<Preferences>
        get() = context.dataStore

}