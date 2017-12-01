package parsers

import model.Battery
import parsers.exceptions.CsvConversionException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

abstract class Parser {

    open fun isHeader(it: String) = it.contains("Battery")
    abstract val columns: Array<String>
    open val splitter = ","

    fun parse(file: File): ArrayList<Battery> {
        val batteries = ArrayList<Battery>()
        val csvFile = file.absolutePath
        try {
            BufferedReader(FileReader(csvFile)).use { br ->
                br.readLines().forEach {
                    if (isHeader(it)) return@forEach
                    try {
                        val battery = createBatteryFromCsv(it.split(splitter))
                        batteries.add(battery)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return batteries
    }

    @Throws(CsvConversionException::class)
    private fun createBatteryFromCsv(csv: List<String>): Battery {

        if (csv.size != columns.size)
            throw IllegalArgumentException("Wrong csv, should have length of ${columns.size}")

        val brand = parseBrand(csv)
        val model = parseModel(csv)
        val size = parseSize(csv)
        val mfgCapacity = parseMfgCapacity(csv)
        val actualCapacity = parseActualCapacity(csv)
        val mfgRatedCdr = parseMfgRatedCdr(csv)
        val actualCdr = parseActualCdr(csv)
        val maxVapeAmps = parseMVA(csv)
        val link = parseLink(csv)

        return Battery(brand = brand,
                model = model,
                size = size,
                mfgCapacity = mfgCapacity,
                actualCapacity = actualCapacity,
                mfgCDR = mfgRatedCdr,
                actualCDR = actualCdr,
                maxVapeAmps = maxVapeAmps,
                reviewLink = link)
    }

    abstract fun parseLink(csv: List<String>): String
    abstract fun parseMVA(csv: List<String>): Double
    abstract fun parseActualCdr(csv: List<String>): Double
    abstract fun parseMfgRatedCdr(csv: List<String>): Double
    abstract fun parseBrand(csv: List<String>): String
    abstract fun parseModel(csv: List<String>): String
    abstract fun parseSize(csv: List<String>): Int
    abstract fun parseMfgCapacity(csv: List<String>): Int
    abstract fun parseActualCapacity(csv: List<String>): Int



}