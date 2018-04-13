package servlet3.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author kingwudy
 * @date 2018/1/11
 */
public class MdcFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(MdcFilter.class);
    public static final String mdcId = "mdcId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean mdcFlag = false;
        try {
            MDC.put(mdcId, UUID.randomUUID().toString());
            mdcFlag = true;
        } catch (Throwable e) {
            log.error("MDC put error", e);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            if (mdcFlag) {
                try {
                    MDC.remove(mdcId);
                } catch (Throwable e) {
                    log.error("MDC remove error", e);
                }
            }
        }
    }
}
