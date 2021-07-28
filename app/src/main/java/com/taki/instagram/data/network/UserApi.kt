import com.taki.instagram.BuildConfig
import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.models.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {

    companion object {
       // const val BASE_URL = "https://api.unsplash.com/"
        const val Client_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @Headers("Accept-version: v1", "Authorization: Client-ID $Client_ID")
    @GET("/photos/random")
    suspend fun getUsers(@Query("count") count: Int): List<Photo>

}