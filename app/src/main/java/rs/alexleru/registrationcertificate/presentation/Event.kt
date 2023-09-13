package rs.alexleru.registrationcertificate.presentation

class Event<T>(data: T) {

    val data: T? = data
        get() {
            if (isConsumed) {
                return null
            }

            consume()
            return field
        }

    var isConsumed = false

    private fun consume() {
        isConsumed = true
    }
}