package com.cookpad.core

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.cookpad.core.screens.utils.Constants
import com.cookpad.core.workers.DataUpdateWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CookPadApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(
            this, workManagerConfiguration
        )
        setupWorker()
    }

    @Inject
    lateinit var workFactory: HiltWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(workFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO).build()
    }

    private fun setupWorker(){
        val workManager = WorkManager.getInstance()
        var continuation = workManager.beginUniqueWork(
            "dataUpdate",
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(DataUpdateWorker::class.java)
        )

        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val saveWork = OneTimeWorkRequestBuilder<DataUpdateWorker>()
            .setConstraints(constraints)
            .addTag(Constants.DATA_UPDATE_TAG).build()

        continuation = continuation.then(saveWork)
        continuation.enqueue()
    }

}