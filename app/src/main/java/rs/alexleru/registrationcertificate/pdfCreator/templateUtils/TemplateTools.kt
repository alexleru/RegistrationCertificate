package rs.alexleru.registrationcertificate.pdfCreator.templateUtils

sealed class TemplateTools {

    open class Line(
        val startX: Float,
        val startY: Float,
        val endX: Float,
        val endY: Float
    ) :
        TemplateTools()

    data class LineHorizontal(
        val sX: Float,
        val eX: Float,
        val y: Float
    ) :
        Line(startX = sX, endX = eX, startY = y, endY = y)

    data class LineVertical(
        val x: Float,
        val sY: Float,
        val eY: Float
    ) :
        Line(startX = x, endX = x, startY = sY, endY = eY)

    data class Text(val value: String, val startX: Float, val startY: Float) : TemplateTools()
}

