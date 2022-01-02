package InterfaceAPI

import Model.ProductModel
import Model.ShopModel
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("storeInfo")
    fun shop(): Call<ShopModel>

    @get:GET("products")
    val products: Call<List<ProductModel>?>?
}