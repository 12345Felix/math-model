package `fun`.basic.model.exception

import java.lang.Exception

class InvalidArgumentException: Exception {
    constructor(message: String): super(message)
}