package batchHttp.process;

import batchHttp.core.LogHttp;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by remy on 06/07/15.
 */
public class HttpProcess implements ItemProcessor<LogHttp, LogHttp> {

    public LogHttp process(LogHttp logHttp) throws Exception {
        LogHttp out = null;

        if (!(logHttp.getStatus() == 200)) {
            out = logHttp;
        }
        return logHttp;
    }
}
