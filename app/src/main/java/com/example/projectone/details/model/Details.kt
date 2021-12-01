data class DetailsResponse(
    val count: Int,
    val `data`: List<Details>,
    val error: Boolean
)

data class Details(
    val __v: Int,
    val _id: String,
    val catId: Int,
    val created: String,
    val description: String,
    val image: String,
    val mrp: Int,
    val position: Int,
    val price: Int,
    val productName: String,
    val quantity: Int,
    val status: Boolean,
    val subId: Int,
    val unit: String,
    var IDMine: Int,
    var QuantityMine:Int
)