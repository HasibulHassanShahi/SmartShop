package Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OrderModel: Serializable {

    @SerializedName("products")
    var productList: List<ProductModel>? = null

    @SerializedName("delivery_address")
    var deliveryAddress: String? = null

    constructor(productList: List<ProductModel>, deliveryAddress: String?) {
        this.productList = productList
        this.deliveryAddress = deliveryAddress
    }
}