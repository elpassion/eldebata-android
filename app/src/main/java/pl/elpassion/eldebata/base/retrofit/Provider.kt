package pl.elpassion.eldebata.base.retrofit;

abstract class Provider<T>(initializer: () -> T) {

    private val value by lazy(initializer)

    var override: T? = null

    fun get() = override ?: value
}
