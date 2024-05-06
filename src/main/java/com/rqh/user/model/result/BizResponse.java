package com.rqh.user.model.result;

import com.rqh.user.model.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class BizResponse<T> extends BaseResponse {

    private static final long serialVersionUID = -2308188085742670108L;

    /**
     * 返回业务对象
     */
    private T data;

    private BizResponse() {
        super();
    }

    private BizResponse(T data) {
        super();
        this.data = data;
    }

    private BizResponse(ResponseCodeEnum code) {
        super(code);
    }

    private BizResponse(ResponseCodeEnum code, String message) {
        super(code, message);
    }

    /**
     * 失败，返回状态码
     * @param code 状态码
     * @return 响应结果
     * @param <T> 泛型
     */
    public static <T> BizResponse<T> fail(ResponseCodeEnum code) {
        return new BizResponse<>(code);
    }

    /**
     * 失败，返回状态码和错误信息
     * @param code 状态码
     * @param errorMsg 错误信息
     * @return 响应结果
     * @param <T> 泛型
     */
    public static <T> BizResponse<T> fail(ResponseCodeEnum code, String errorMsg) {
        return new BizResponse<>(code, errorMsg);
    }

    /**
     * 成功，返回 null
     * @return 响应结果
     * @param <T> 泛型
     */
    public static <T> BizResponse<T> success() {
        return new BizResponse<>();
    }

    /**
     * 成功，返回业务对象
     * @param data 业务对象
     * @return 响应结果
     * @param <T> 泛型
     */
    public static <T> BizResponse<T> success(T data) {
        return new BizResponse<>(data);
    }
}
