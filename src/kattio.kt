import java.io.*
import java.math.BigInteger
import java.util.*


class Kattio(i: InputStream = System.`in`, o: OutputStream = System.out) : PrintWriter(BufferedOutputStream(o)) {
    private val reader: BufferedReader = BufferedReader(InputStreamReader(i))
    private var tokenizer: StringTokenizer? = null
    private var token: String? = null

    private fun peekToken(): String? {
        if (token == null) {
            try {
                while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
                    var line = reader.readLine() ?: return null
                    tokenizer = StringTokenizer(line)
                }
                token = tokenizer!!.nextToken()
            } catch (e: IOException) {
            }
        }
        return token
    }

    private fun nextTokenOrNull(): String? = peekToken().also { token = null }
    private fun nextToken() = nextTokenOrNull()!!

    val hasMoreTokens: Boolean get() = peekToken() != null

    fun int():    Int        = nextToken().toInt()
    fun long():   Long       = nextToken().toLong()
    fun bigint(): BigInteger = nextToken().toBigInteger()
    fun double(): Double     = nextToken().toDouble()
    fun word():   String     = nextToken()

    fun intOrNull():    Int?        = nextTokenOrNull()?.toInt()
    fun longOrNull():   Long?       = nextTokenOrNull()?.toLong()
    fun bigintOrNull(): BigInteger? = nextTokenOrNull()?.toBigInteger()
    fun doubleOrNull(): Double?     = nextTokenOrNull()?.toDouble()
    fun wordOrNull():   String?     = nextTokenOrNull()

    fun ints():    Sequence<Int>        = generateSequence { this.intOrNull() }
    fun longs():   Sequence<Long>       = generateSequence { this.longOrNull() }
    fun bigints(): Sequence<BigInteger> = generateSequence { this.bigintOrNull() }
    fun doubles(): Sequence<Double>     = generateSequence { this.doubleOrNull() }
    fun words():   Sequence<String>     = generateSequence { this.wordOrNull() }
}

