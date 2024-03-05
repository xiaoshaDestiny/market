package com.rbxu.market.exception;

import com.alibaba.cola.exception.BizException;
import com.rbxu.market.enums.ErrorCodeEnum;
import java.text.MessageFormat;

public class ExceptionBuilder {

    public static BizException build(ErrorCodeEnum errorCodeEnum, Object... formatParam) {
        String errDescFormat = errorCodeEnum.getErrDesc();
        String format = MessageFormat.format(errDescFormat, (Object) formatParam);
        return new BizException(errorCodeEnum.getErrCode(), format);
    }

    public static BizException build(ErrorCodeEnum errorCodeEnum, Throwable e, Object... formatParam) {
        String errDescFormat = errorCodeEnum.getErrDesc();
        String format = MessageFormat.format(errDescFormat, (Object) formatParam);
        return new BizException(errorCodeEnum.getErrCode(), format, e);
    }
}
