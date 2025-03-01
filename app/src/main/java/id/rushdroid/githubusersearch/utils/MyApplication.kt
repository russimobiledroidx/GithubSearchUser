package id.rushdroid.githubusersearch.utils

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()

//    companion object {
//        lateinit var appContext: Context
//        lateinit var repository: UserRepository
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        appContext = this
//
//        val database = AppDatabase.getInstance(appContext) // Dapatkan instance database
//        val apiService = GithubApiClient.apiService // Ambil instance API service (Retrofit)
//
//        repository = UserRepository(database, apiService) // Gunakan AppDatabase & GitHubApiService
//    }


//}