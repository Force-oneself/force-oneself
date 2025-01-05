package com.quan.tools.log;

/**
 * 日志接口
 *
 * @author Force-oneself
 * @date 2023-09-21
 */
public interface Logger {

    /**
     * 返回此 <code>Logger</code> 实例的名称。
     *
     * @return 此日志记录器实例的名称
     */
    String getName();

    /**
     * 日志记录器实例是否启用 TRACE 级别？
     *
     * @return 如果此 Logger 启用 TRACE 级别，则返回 True，
     * 否则返回 false。
     */
    boolean isTraceEnabled();

    /**
     * 根据指定的格式和参数以 TRACE 级别记录消息。
     * <p/>
     * <p>此形式避免了在日志记录器禁用 TRACE 级别时多余的字符串连接。然而，此变体在调用方法之前会产生创建 <code>Object[]</code> 的隐藏（且相对较小的）成本，
     * 即使此日志记录器在 TRACE 级别被禁用。仅存在接受  和
     * 参数的变体，以避免此隐藏成本。</p>
     *
     * @param format    格式字符串
     * @param arguments 三个或更多参数的列表
     */
    void trace(String format, Object... arguments);

    /**
     * 以 TRACE 级别记录异常（可抛出对象）及其伴随消息。
     *
     * @param msg 伴随异常的消息
     * @param t   要记录的异常（可抛出对象）
     */
    void trace(String msg, Throwable t);

    /**
     * 日志记录器实例是否启用 DEBUG 级别？
     *
     * @return 如果此 Logger 启用 DEBUG 级别，则返回 True，
     * 否则返回 false。
     */
    boolean isDebugEnabled();

    /**
     * 根据指定的格式和参数以 DEBUG 级别记录消息。
     * <p/>
     * <p>此形式避免了在日志记录器禁用 DEBUG 级别时多余的字符串连接。然而，此变体在调用方法之前会产生创建 <code>Object[]</code> 的隐藏（且相对较小的）成本，
     * 即使此日志记录器在 DEBUG 级别被禁用。仅存在接受
     * 和  参数的变体，以避免此隐藏成本。</p>
     *
     * @param format    格式字符串
     * @param arguments 三个或更多参数的列表
     */
    void debug(String format, Object... arguments);

    /**
     * 以 DEBUG 级别记录异常（可抛出对象）及其伴随消息。
     *
     * @param msg 伴随异常的消息
     * @param t   要记录的异常（可抛出对象）
     */
    void debug(String msg, Throwable t);

    /**
     * 日志记录器实例是否启用 INFO 级别？
     *
     * @return 如果此 Logger 启用 INFO 级别，则返回 True，
     * 否则返回 false。
     */
    boolean isInfoEnabled();

    /**
     * 根据指定的格式和参数以 INFO 级别记录消息。
     * <p/>
     * <p>此形式避免了在日志记录器禁用 INFO 级别时多余的字符串连接。然而，此变体在调用方法之前会产生创建 <code>Object[]</code> 的隐藏（且相对较小的）成本，
     * 即使此日志记录器在 INFO 级别被禁用。仅存在接受
     * 和  参数的变体，以避免此隐藏成本。</p>
     *
     * @param format    格式字符串
     * @param arguments 三个或更多参数的列表
     */
    void info(String format, Object... arguments);

    /**
     * 以 INFO 级别记录异常（可抛出对象）及其伴随消息。
     *
     * @param msg 伴随异常的消息
     * @param t   要记录的异常（可抛出对象）
     */
    void info(String msg, Throwable t);

    /**
     * 日志记录器实例是否启用 WARN 级别？
     *
     * @return 如果此 Logger 启用 WARN 级别，则返回 True，
     * 否则返回 false。
     */
    boolean isWarnEnabled();

    /**
     * 根据指定的格式和参数以 WARN 级别记录消息。
     * <p/>
     * <p>此形式避免了在日志记录器禁用 WARN 级别时多余的字符串连接。然而，此变体在调用方法之前会产生创建 <code>Object[]</code> 的隐藏（且相对较小的）成本，
     * 即使此日志记录器在 WARN 级别被禁用。仅存在接受
     *
     * @param format    格式字符串
     * @param arguments 三个或更多参数的列表
     */
    void warn(String format, Object... arguments);

    /**
     * 以 WARN 级别记录异常（可抛出对象）及其伴随消息。
     *
     * @param msg 伴随异常的消息
     * @param t   要记录的异常（可抛出对象）
     */
    void warn(String msg, Throwable t);

    /**
     * 日志记录器实例是否启用 ERROR 级别？
     *
     * @return 如果此 Logger 启用 ERROR 级别，则返回 True，
     * 否则返回 false。
     */
    boolean isErrorEnabled();

    /**
     * 根据指定的格式和参数以 ERROR 级别记录消息。
     * <p/>
     * <p>此形式避免了在日志记录器禁用 ERROR 级别时多余的字符串连接。然而，此变体在调用方法之前会产生创建 <code>Object[]</code> 的隐藏（且相对较小的）成本，
     * 即使此日志记录器在 ERROR 级别被禁用。仅存在接受
     *
     * @param format    格式字符串
     * @param arguments 三个或更多参数的列表
     */
    void error(String format, Object... arguments);

    /**
     * 以 ERROR 级别记录异常（可抛出对象）及其伴随消息。
     *
     * @param msg 伴随异常的消息
     * @param t   要记录的异常（可抛出对象）
     */
    void error(String msg, Throwable t);
}
