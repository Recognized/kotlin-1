// !WITH_NEW_INFERENCE
//KT-742 Stack overflow in type inference
package a

fun <T : Any> T?.sure() : T = this!!

class List<T>(val head: T, val tail: List<T>? = null)

fun <T, Q> List<T>.map1(f: (T)-> Q): List<T>? = tail!!.map1(f)

fun <T, Q> List<T>.map2(f: (T)-> Q): List<T>? = tail.sure().map2(f)

fun <T, Q> List<T>.map3(f: (T)-> Q): List<T>? = <!TYPE_MISMATCH{OI}!>tail<!>.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER{NI}!>sure<!><<!UPPER_BOUND_VIOLATED{OI}!>T<!>>().<!DEBUG_INFO_MISSING_UNRESOLVED{NI}, UNRESOLVED_REFERENCE_WRONG_RECEIVER{OI}!>map3<!>(f)
