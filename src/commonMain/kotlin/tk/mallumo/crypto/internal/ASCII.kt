package tk.mallumo.crypto.internal

internal object ASCII {
    operator fun invoke(v: String): ByteArray = encode(v)
    operator fun invoke(v: ByteArray): String = decode(v)

    fun encode(str: String): ByteArray =
        ByteArray(str.length).also { out -> for (n in out.indices) out[n] = str[n].code.toByte() }
    fun decode(data: ByteArray): String =
        CharArray(data.size).also { out -> for (n in out.indices) out[n] = data[n].toInt().toChar() }.concatToString()
}

internal fun String.fromAscii(): ByteArray = ASCII(this)
internal val ByteArray.ascii: String get() = ASCII(this)
