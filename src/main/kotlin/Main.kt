import model.Battery
import parsers.Parser18650
import parsers.Parser2XXX
import java.io.File

fun main(args: Array<String>) {
    parse18650()
    parse2xxx()
}

fun parse2xxx() {
    val batteries2XXXfile = File("/Users/admin1/Downloads/00d4f66d3b8705e99b0084bc0f70db31/20650.csv")
    val parser = Parser2XXX()
    val batteries2XXX = parser.parse(batteries2XXXfile)
}

fun parse18650() {
    val batteries18650file1 = File("/Users/admin1/Downloads/00d4f66d3b8705e99b0084bc0f70db31/18650_1.csv")
    val batteries18650file2 = File("/Users/admin1/Downloads/00d4f66d3b8705e99b0084bc0f70db31/18650_2.csv")
    val batteries18650file3 = File("/Users/admin1/Downloads/00d4f66d3b8705e99b0084bc0f70db31/18650_3.csv")
    val parser = Parser18650()
    val batteries18650 = ArrayList<Battery>()
    batteries18650.addAll(parser.parse(batteries18650file1))
    batteries18650.addAll(parser.parse(batteries18650file2))
    batteries18650.addAll(parser.parse(batteries18650file3))

}
