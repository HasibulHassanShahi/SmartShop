package Model

import com.google.gson.annotations.SerializedName

class ShopModel {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("rating")
    var rating = 0
    @SerializedName("openingTime")
    var openTime: String? = null
    @SerializedName("closingTime")
    var closeTime: String? = null

    constructor(name: String?, rating: Int, openTime: String?, closeTime: String?) {
        this.name = name
        this.rating = rating
        this.openTime = openTime
        this.closeTime = closeTime
    }
}