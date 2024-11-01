package io.github.ye17186.myhelper.web.interceptor.api;

import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.utils.StringPool;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.utils.crypto.CryptoUtils;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import io.github.ye17186.myhelper.core.web.response.ApiResp;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAuthInterceptorProperties;
import io.github.ye17186.myhelper.web.interceptor.MhInterceptor;
import io.github.ye17186.myhelper.web.utils.RequestUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ye17186
 */
@Slf4j
public class MhApiAuthInterceptor extends MhInterceptor {

    private final MhWebApiAuthInterceptorProperties properties;

    public MhApiAuthInterceptor(MhWebApiAuthInterceptorProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        boolean flag = false;
        try {
            Model model = getHeaderTriple(request);
            flag = checkTimestamp(model) && checkCode(model) && checkSignature(model, request);
        } catch (Exception e) {
            log.warn("[My Helper] 接口鉴权异常", e);
        }

        if (!flag) {
            ApiResp<String> resp = ApiResp.fail(ErrorCode.API_FAIL);
            writeResp(request, response, resp);
        }
        return flag;
    }

    private Model getHeaderTriple(HttpServletRequest request) {

        return Model.builder()
                .code(request.getHeader(properties.getHeaderPrefix() + "-Code"))
                .timestamp(Long.parseLong(request.getHeader(properties.getHeaderPrefix() + "-Timestamp")))
                .signature(request.getHeader(properties.getHeaderPrefix() + "-Signature"))
                .build();
    }

    private boolean checkTimestamp(Model model) {

        long clientTimestamp = model.getTimestamp();
        long serverTimestamp = System.currentTimeMillis();
        return Math.abs(serverTimestamp - clientTimestamp) < properties.getSignTimeout();
    }

    private boolean checkCode(Model ignore) {

        // todo code唯一
        return true;
    }

    private boolean checkSignature(Model model, HttpServletRequest request) {

        String clientSign = model.getSignature();

        Map<String, Object> sortedParamMap = new TreeMap<>();
        StringBuffer sb = new StringBuffer();
        String requestBody = RequestUtils.getRequestBody(request);
        if(StringUtils.isNotEmpty(requestBody)) {
            Map<String, Object> paramMap = JsonUtils.json2Map(requestBody, Object.class);
            sortedParamMap.putAll(paramMap);
        }
        if(CollectionUtils.isNotEmpty(sortedParamMap)) {
            sortedParamMap.forEach((k, v) -> sb.append(k).append(StringPool.EQUALS).append(v).append(StringPool.AMPERSAND));
        }
        sb.append("code=").append(model.getCode()).append("&timestamp=").append(model.getTimestamp());
        String serverSign = CryptoUtils.AES.encrypt(properties.getSignKey(), sb.toString());
        return clientSign.equals(serverSign);
    }


    @Getter
    @Builder
    private static class Model implements Serializable {

        /**
         * 唯一编码，防止重复提交
         */
        private String code;

        /**
         * 客户端时间戳，防止重放
         */
        private long timestamp;

        /**
         * 客户端签名，防止参数被篡改
         */
        private String signature;
    }
}
