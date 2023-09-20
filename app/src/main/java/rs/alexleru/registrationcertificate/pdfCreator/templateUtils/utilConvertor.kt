package rs.alexleru.registrationcertificate.pdfCreator.templateUtils


internal fun TemplateTools.converter(): TemplateTools {
    return when (this) {
        is TemplateTools.Line -> {
            TemplateTools.Line(
                this.startX.toPostScript(),
                this.startY.toPostScript(),
                this.endX.toPostScript(),
                this.endY.toPostScript()
            )
        }

        is TemplateTools.Text -> {
            TemplateTools.Text(
                this.value,
                this.startX.toPostScript(),
                this.startY.toPostScript()
            )
        }
    }
}

internal fun TemplateTools.Text.multiLine(offset: Float = 4f): List<TemplateTools.Text> {
    val strings = value.split("\n")
    var offsetCount = startY
    val result = arrayListOf<TemplateTools.Text>()
    strings.forEach {
        result.add(TemplateTools.Text(it, startX, offsetCount))
        offsetCount += offset
    }
    return result
}

internal fun Float.toPostScript() = (this * 2.8346456693).toFloat()

internal fun Int.toPostScript() = (this * 2.8346456693).toInt()