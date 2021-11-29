data class orders(
    val `data`: Data,
    val error: Boolean,
    val message: String
)

data class Data(
    val __v: Int,
    val _id: String,
    val date: String,
    val orderSummary: OrderSummary,
    val payment: Payment,
    val products: List<Product>,
    val shippingAddress: ShippingAddress,
    val userId: String
)

data class OrderSummary(
    val _id: String,
    val deliveryCharges: Int,
    val discount: Int,
    val ourPrice: Int,
    val totalAmount: Int
)

data class Payment(
    val _id: String,
    val paymentMode: String
)

data class Product(
    val _id: String,
    val price: Int,
    val productName: String,
    val quantity: Int
)

data class ShippingAddress(
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String
)