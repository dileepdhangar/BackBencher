import android.util.Log
import com.example.backbencher.Retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchDataFromApiNotes(query: String): String {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiService.fetchDataNotes(query)
            if (response.isSuccessful) {
                Log.d("Praveen", response.body().toString())
                response.body() ?: "No response"

            } else {
                Log.d("error in retrofit", "${response.code()} ")
                "API Error: ${response.code()}"
            }

        } catch (e: Exception) {
            Log.d("exception", "${e.message}: ")
            "Error: ${e.message}"
        }
    }
}

suspend fun fetchDataFromApiSummary(query: String): String {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiService.fetchDataSummary(query)
            if (response.isSuccessful) {
                Log.d("Praveen", response.body().toString())
                response.body() ?: "No response"

            } else {
                Log.d("error in retrofit", "${response.code()} ")
                "API Error: ${response.code()}"
            }

        } catch (e: Exception) {
            Log.d("exception", "${e.message}: ")
            "Error: ${e.message}"
        }
    }
}