package model


data class Battery(
        var brand: String = "",
        var model: String = "",
        var imgPath: Int = 0,
        var size: Int = 0,
        var mfgCapacity: Int = 0,
        var actualCapacity: Int = 0,
        var voltage: Double = 0.0,
        var mfgCDR: Double = 0.0,
        var actualCDR: Double = 0.0,
        var maxVapeAmps: Double = 0.0,
        var reviewLink: String = ""
)



