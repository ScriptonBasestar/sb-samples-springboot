package sample.domain.jpa.usertype

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.ParameterizedType
import org.hibernate.usertype.UserType
import org.mindrot.jbcrypt.BCrypt
import java.io.Serializable
import java.nio.charset.Charset
import java.nio.charset.IllegalCharsetNameException
import java.nio.charset.UnsupportedCharsetException
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types
import java.util.*
import java.util.regex.*

class BCryptUserType : UserType, ParameterizedType {
    protected var charset: Charset? = null
    override fun setParameterValues(properties: Properties) {
        if (properties != null) {
            val tmp = properties.getProperty(PARAM_CHARSET)
            if (tmp != null) {
                charset = try {
                    Charset.forName(tmp)
                } catch (x: IllegalCharsetNameException) {
                    throw HibernateException("Unsupported character set " + tmp + ": " + x.message, x)
                } catch (x: UnsupportedCharsetException) {
                    throw HibernateException("Unsupported character set " + tmp + ": " + x.message, x)
                }
            }
        }
        if (charset == null) {
            charset = DEFAULT_CHARSET
        }
    }

    override fun sqlTypes(): IntArray {
        return intArrayOf(SQL_TYPE)
    }

    override fun returnedClass(): Class<*> {
        return String::class.java
    }

    @Throws(HibernateException::class)
    override fun equals(o: Any, o1: Any): Boolean {
        return o === o1 || o != null && o == o1
    }

    @Throws(HibernateException::class)
    override fun hashCode(o: Any): Int {
        return o.hashCode()
    }

    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeGet(
        rs: ResultSet,
        names: Array<String>,
        session: SharedSessionContractImplementor,
        owner: Any
    ): Any {
        val `val` = rs.getString(names[0])
        return (if (rs.wasNull()) null else `val`)!!
    }

    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeSet(st: PreparedStatement, value: Any, index: Int, session: SharedSessionContractImplementor) {
        if (value == null) {
            st.setNull(index, SQL_TYPE)
        } else if (BCRYPTED_PASSWORD_PATTERN.matcher(value.toString()).matches()) {
            st.setString(index, value.toString())
        } else {
            st.setString(index, BCrypt.hashpw(value.toString(), BCrypt.gensalt()))
        }
    }

    @Throws(HibernateException::class)
    override fun deepCopy(o: Any): Any {
        return o
    }

    override fun isMutable(): Boolean {
        return false
    }

    @Throws(HibernateException::class)
    override fun disassemble(o: Any): Serializable {
        return o as Serializable
    }

    @Throws(HibernateException::class)
    override fun assemble(serializable: Serializable, o: Any): Any {
        return serializable
    }

    @Throws(HibernateException::class)
    override fun replace(original: Any, target: Any, owner: Any): Any {
        return original
    }

    companion object {
        const val TYPE = "sample.domain.usertype.BCryptUserType"
        const val PARAM_CHARSET = "charset"
        val DEFAULT_CHARSET = Charset.forName("UTF8")
        const val SQL_TYPE = Types.VARCHAR
        val BCRYPTED_PASSWORD_PATTERN = Pattern.compile("^\\$2a\\$\\d{2}\\$.*")
    }
}
