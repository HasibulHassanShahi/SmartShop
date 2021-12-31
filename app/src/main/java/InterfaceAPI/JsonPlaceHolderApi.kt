package InterfaceAPI

import Model.ProductModel
import Model.ShopModel
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @get:GET("storeInfo")
    val shop: Call<List<ShopModel>?>?

    @get:GET("products")
    val products: Call<List<ProductModel>?>?
}