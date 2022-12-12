package com.cookpad.core.workers


import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.cookpad.core.screens.utils.Constants.DATA_UPDATE_TAG
import com.cookpad.domain.repository.IngredientRepository
import com.cookpad.domain.repository.RecipeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class DataUpdateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val ingredientRepository: IngredientRepository,
    private val recipeRepository: RecipeRepository
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        try {
            delay(5000)
//            ingredientRepository.getIngredients()
//            recipeRepository.getAllRecipes()
            Log.d("WORK EXECUTED", "${recipeRepository.getAllRecipes()}")
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
        }catch (e: Exception){
            Log.d("WORK EXECUTED", "${e.message}")
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
            return Result.failure(workDataOf("data" to (e.message?: "")))
        }
    }
}