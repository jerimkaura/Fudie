package com.cookpad.core.workers


import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.cookpad.core.screens.utils.Constants.DATA_UPDATE_TAG
import com.cookpad.core.screens.utils.Constants.INGREDIENTS_CHANNEL_ID
import com.cookpad.domain.repository.IngredientsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import com.cookpad.core.R

@HiltWorker
class DataUpdateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val ingredientsRepository: IngredientsRepository
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        delay(5000)
        ingredientsRepository.getIngredients()
        delay(5000)
        val appContext = applicationContext
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
            .addTag(DATA_UPDATE_TAG).build()

        continuation = continuation.then(saveWork)
        continuation.enqueue()

        return Result.success(workDataOf("data" to "Success"))
    }
}