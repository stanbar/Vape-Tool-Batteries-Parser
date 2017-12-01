package parsers

import parsers.exceptions.CsvConversionException

class Parser18650 : Parser() {

    override fun parseBrand(csv: List<String>): String {
        val brandAndModel = csv[0]
        if (brandAndModel.isBlank())
            throw CsvConversionException("model.Battery brand and name can not be empty", csv)
        return brandAndModel.split(" ")[0]
    }

    override fun parseModel(csv: List<String>): String {
        val brandAndModel = csv[0]
        if (brandAndModel.isBlank())
            throw CsvConversionException("model.Battery brand and name can not be empty", csv)

        val brand = parseBrand(csv)

        return if (brand == brandAndModel)
            ""
        else
            brandAndModel.substring(brand.length + 1)
    }

    override fun parseSize(csv: List<String>) = 18650

    override fun parseMfgCapacity(csv: List<String>): Int {
        val capacityString = csv[1]
        if (capacityString.isBlank())
            throw CsvConversionException("model.Battery tested capacity can not be empty", csv)
        return capacityString.toInt()
    }

    override fun parseActualCapacity(csv: List<String>): Int {
        val capacityString = csv[1]
        if (capacityString.isBlank())
            throw CsvConversionException("model.Battery tested capacity can not be empty", csv)
        return capacityString.toInt()
    }

    override fun parseMfgRatedCdr(csv: List<String>): Double {
        val mfgCdrString = csv[2]
        if (mfgCdrString.isBlank())
            throw CsvConversionException("Mfg CDR can not be empty", csv)
        return mfgCdrString.replace("A", "")
                .replace("+", "").toDouble()
    }

    override fun parseActualCdr(csv: List<String>): Double {
        val mfgCdrString = csv[3]
        if (mfgCdrString.isBlank())
            throw CsvConversionException("Actual CDR can not be empty", csv)
        return mfgCdrString.replace("A", "")
                .replace("+", "").toDouble()
    }

    override fun parseMVA(csv: List<String>): Double {
        val mfgCdrString = csv[4]
        if (mfgCdrString.isBlank())
            throw CsvConversionException("MaxVapingAmps can not be empty", csv)
        return mfgCdrString.replace("A", "")
                .replace("+", "").toDouble()
    }
    override fun parseLink(csv: List<String>): String {
        TODO()
    }

    override val columns: Array<String> = arrayOf(
            "Battery", //Brand and name
            "mAh",
            "Mfg CDR",
            "Actual Cont. Dischg Rating(CDR)",
            "Max Vaping Amps (MVA)")

}