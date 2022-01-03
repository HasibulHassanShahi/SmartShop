package ApiService

import InterfaceAPI.JsonPlaceHolderApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServices {

    private val baseUrl: String = "https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/"

    lateinit var retrofit: Retrofit
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    fun getRetrofit(): JsonPlaceHolderApi{
        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        return jsonPlaceHolderApi
    }
}