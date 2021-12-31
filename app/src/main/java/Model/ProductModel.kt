package Model

import com.google.gson.annotations.SerializedName

class ProductModel {

    @SerializedName("name")
    var name: String? = null
    var price = 0.0
    var imageUrl: String? = null

    constructor(name: String?, price: Double, imageUrl: String?) {
        this.name = name
        this.price = price
        this.imageUrl = imageUrl
    }
}