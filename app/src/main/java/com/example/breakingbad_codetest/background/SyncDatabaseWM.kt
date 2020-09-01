package com.example.breakingbad_codetest.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.breakingbad_codetest.repository.CharactersRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 * WorkManager automatically calls Worker.doWork() on a background thread.
 * Periodically syncing application data with a server
 */
class SyncDatabaseWM (appContext: Context, params:WorkerParameters): CoroutineWorker(appContext, params),
    KoinComponent {

    val dataSyncRepository: CharactersRepository by inject()

    companion object{
        const val WORK_NAME="com.example.breakingbad_codetest.background.SyncDatabaseWM"
    }
    override suspend fun doWork(): Result {
        /**
         * Sync the backend API data with local database even if user is not using the app or device restarts
         */
        try{
            dataSyncRepository.refreshCharacters();
            Timber.d("WorkManager: sync in progress")
        }
        catch (e:Exception){
            Timber.e("WorkManager error: ${e.localizedMessage}")
            return Result.retry()
        }
        return Result.success()
    }
}