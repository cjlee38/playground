package study.syntax.generics

fun<T> ensureTrailingPeriod(seq: T) where T: CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}