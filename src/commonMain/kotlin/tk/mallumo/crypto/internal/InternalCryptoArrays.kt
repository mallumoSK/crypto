package tk.mallumo.crypto.internal

/** Copies [size] elements of [src] starting at [srcPos] into [dst] at [dstPos]  */
internal inline fun arraycopy(src: ByteArray, srcPos: Int, dst: ByteArray, dstPos: Int, size: Int) {
    src.copyInto(dst, dstPos, srcPos, srcPos + size)
}

/** Copies [size] elements of [src] starting at [srcPos] into [dst] at [dstPos]  */
internal inline fun arraycopy(src: IntArray, srcPos: Int, dst: IntArray, dstPos: Int, size: Int) {
    src.copyInto(dst, dstPos, srcPos, srcPos + size)
}
