package InterfaceAPI

import Model.OrderModel
import Model.ProductModel
import Model.ShopModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface JsonPlaceHolderApi {
    @GET("storeInfo")
    fun shop(): Call<ShopModel>

    @GET("products")
    fun products(): Call<List<ProductModel>>

    @Headers("Content-Type: application/json")
    @POST("order")
    fun createOrder(@Body orderModel: OrderModel): Call<String>
}