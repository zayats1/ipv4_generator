import java.io.File


data class ipNetworkClass(
    val network_class: Char,
    val firstNetworkNumberMin: Int,
    val firstNetworkNumberMax: Int,
    val mask: String,
)

val ipNetworkClassA = ipNetworkClass('A', 1, 126, "255.0.0.0")
val ipNetworkClassB = ipNetworkClass('B', 128, 191, "255.255.0.0")
val ipNetworkClassC = ipNetworkClass('C', 192, 223, "255.255.255.0")

fun generateIPAddressesTable(quantity: Int, ip_network_class: ipNetworkClass): Array<String> {
    val maxNum = 254
    val (_, firstNetworkNumberMin, firstNetworkNumberMax, mask) = ip_network_class

    val first_num = (firstNetworkNumberMin..firstNetworkNumberMax).random()
    val num = (0..maxNum).random()

    val ip_addreses = Array<String>(quantity, {
        i ->
        "$first_num.$num.$num.$i $mask" // the line doesn't format
    })
    return ip_addreses
}

fun writeIpAddressesTableIntoFile(file_name: String, ip_addreses: Array<String>) {
    File(file_name).writeText(ip_addreses.joinToString("\n"))
}

val quantity = 10

val ipNetworkClasses = arrayOf(ipNetworkClassA, ipNetworkClassB, ipNetworkClassC)

for (i in ipNetworkClasses) {
    writeIpAddressesTableIntoFile("ip_addresesClass${i.network_class}.txt", generateIPAddressesTable(quantity, i))
}

