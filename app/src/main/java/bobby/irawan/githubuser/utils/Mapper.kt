package bobby.irawan.githubuser.utils

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
interface Mapper<in T : Any, out U : Any> {
    fun map(data: T): U
}