package com.quan.log.data.datasource;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class ProxyPrepareStatement extends ProxyStatement implements PreparedStatement {

    private final PreparedStatement delegate;

    public ProxyPrepareStatement(PreparedStatement delegate, String sql) {
        super(delegate);
        this.delegate = delegate;
        this.recorder.recordSql(sql);
    }

    @Override
    public ResultSet executeQuery() throws java.sql.SQLException {
        return recorder.execute(this.delegate::executeQuery, null, ExecuteType.ExecuteQuery);
    }

    @Override
    public int executeUpdate() throws java.sql.SQLException {
        return recorder.execute(this.delegate::executeUpdate, null, ExecuteType.ExecuteUpdate);
    }

    @Override
    public boolean execute() throws SQLException {
        return recorder.execute(this.delegate::execute, null, ExecuteType.Execute);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws java.sql.SQLException {
        recorder.setParameter(sqlType);
        this.delegate.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws java.sql.SQLException {
        recorder.setParameter(x);
        this.delegate.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters() throws SQLException {
        recorder.clearParameters();
        this.delegate.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setObject(parameterIndex, x);
    }

    @Override
    public void addBatch() throws SQLException {
        this.delegate.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return this.delegate.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        recorder.setParameter(sqlType);
        this.delegate.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return this.delegate.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        recorder.setParameter(value);
        this.delegate.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        recorder.setParameter(value);
        this.delegate.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        recorder.setParameter(value);
        this.delegate.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        recorder.setParameter(inputStream);
        this.delegate.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        recorder.setParameter(xmlObject);
        this.delegate.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        recorder.setParameter(value);
        this.delegate.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        recorder.setParameter(inputStream);
        this.delegate.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        recorder.setParameter(reader);
        this.delegate.setNClob(parameterIndex, reader);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        recorder.setParameter(x);
        this.delegate.setObject(parameterIndex, x, targetSqlType);
    }

}

