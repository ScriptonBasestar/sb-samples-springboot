package sample.member.app.security

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.ParameterizedType
import org.hibernate.usertype.UserType
import org.springframework.security.crypto.bcrypt.BCrypt
import java.io.Serializable
import java.nio.charset.Charset
import java.nio.charset.IllegalCharsetNameException
import java.nio.charset.UnsupportedCharsetException
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types
import java.util.*
import java.util.regex.Pattern

open class BCryptUserType : UserType, ParameterizedType {

	companion object {
		const val TYPE = "sample.member.app.security.BCryptUserType"
		private const val PARAM_CHARSET = "charset"
		private const val SQL_TYPE = Types.VARCHAR
		private const val DEFAULT_CHARSET = "UTF8"
		private val BCRYPTED_PASSWORD_PATTERN = Pattern.compile("^\\$2a\\$\\d{2}\\$.*")
	}

	protected lateinit var charset: Charset

	override fun isMutable() = false

	override fun setParameterValues(properties: Properties?) {
		val charsetName:String = properties?.getProperty(PARAM_CHARSET) ?: DEFAULT_CHARSET

		try {
			charset = Charset.forName(charsetName)
		} catch (x: IllegalCharsetNameException) {
			throw HibernateException("Unsupported character set $charsetName: ${x.message}", x)
		} catch (x: UnsupportedCharsetException) {
			throw HibernateException("Unsupported character set $charsetName: ${x.message}", x)
		}
	}

	override fun sqlTypes() = intArrayOf(SQL_TYPE)

	override fun returnedClass() = String::class.java

	@Throws(HibernateException::class)
	override fun equals(o: Any, o1: Any) = o === o1 && o == o1

	@Throws(HibernateException::class)
	override fun hashCode(o: Any) = o.hashCode()

	@Throws(HibernateException::class, SQLException::class)
	override fun nullSafeGet(rs: ResultSet, names: Array<String>, session: SharedSessionContractImplementor, owner: Any) = if (rs.wasNull()) null else rs.getString(names[0])

	@Throws(HibernateException::class, SQLException::class)
	override fun nullSafeSet(st: PreparedStatement, value: Any?, index: Int, session: SharedSessionContractImplementor) {
		when {
			value == null -> st.setNull(index, SQL_TYPE)
			BCRYPTED_PASSWORD_PATTERN.matcher(value.toString()).matches() -> st.setString(index, value.toString())
			else -> st.setString(index, BCrypt.hashpw(value.toString(), BCrypt.gensalt()))
		}
	}

	@Throws(HibernateException::class)
	override fun deepCopy(o: Any) = o

	@Throws(HibernateException::class)
	override fun disassemble(o: Any) = o as Serializable

	@Throws(HibernateException::class)
	override fun assemble(serializable: Serializable, o: Any) = serializable

	@Throws(HibernateException::class)
	override fun replace(original: Any, target: Any, owner: Any) = original

}
