package Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductModel: Serializable {

    @SerializedName("name")
    var name: String? = null
    var price = 0.0
    var imageUrl: String? = null
    var qtn = 0

    constructor(name: String?, price: Double, imageUrl: String?, qtn: Int) {
        this.name = name
        this.price = price
        this.imageUrl = imageUrl
        this.qtn = qtn
    }
}