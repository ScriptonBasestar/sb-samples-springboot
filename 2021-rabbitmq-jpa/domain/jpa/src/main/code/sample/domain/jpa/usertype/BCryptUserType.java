package sample.domain.jpa.usertype;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.regex.Pattern;

public class BCryptUserType implements UserType, ParameterizedType {

    public static final String TYPE = "sample.domain.usertype.BCryptUserType";

    public static final String PARAM_CHARSET = "charset";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF8");

    public static final int SQL_TYPE = Types.VARCHAR;

    public static final Pattern BCRYPTED_PASSWORD_PATTERN = Pattern.compile("^\\$2a\\$\\d{2}\\$.*");

    protected Charset charset;

    public void setParameterValues(final Properties properties) {
        if (properties != null) {
            String tmp = properties.getProperty(PARAM_CHARSET);
            if (tmp != null) {
                try {
                    charset = Charset.forName(tmp);
                } catch (IllegalCharsetNameException x) {
                    throw new HibernateException("Unsupported character set " + tmp + ": " + x.getMessage(), x);
                } catch (UnsupportedCharsetException x) {
                    throw new HibernateException("Unsupported character set " + tmp + ": " + x.getMessage(), x);
                }
            }
        }
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
    }

    public int[] sqlTypes() {
        return new int[]{SQL_TYPE};
    }

    public Class returnedClass() {
        return String.class;
    }

    public boolean equals(final Object o, final Object o1) throws HibernateException {
        return o == o1 || (o != null && o.equals(o1));
    }

    public int hashCode(final Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        final String val = rs.getString(names[0]);
        return rs.wasNull() ? null : val;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, SQL_TYPE);
        } else if (BCRYPTED_PASSWORD_PATTERN.matcher(value.toString()).matches()) {
            st.setString(index, value.toString());
        } else {
            st.setString(index, BCrypt.hashpw(value.toString(), BCrypt.gensalt()));
        }
    }

    public Object deepCopy(final Object o) throws HibernateException {
        return o;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(final Object o) throws HibernateException {
        return (Serializable) o;
    }

    public Object assemble(final Serializable serializable, final Object o) throws HibernateException {
        return serializable;
    }

    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return original;
    }
}
