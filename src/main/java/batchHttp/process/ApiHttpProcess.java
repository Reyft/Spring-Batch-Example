package batchHttp.process;

import batchHttp.core.ApiHttp;
import batchHttp.core.LogHttp;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by remy on 07/07/15.
 */
public class ApiHttpProcess implements ItemProcessor<ApiHttp ,LogHttp> {

    public LogHttp process(ApiHttp api) throws Exception{
        LogHttp ret = new LogHttp();
        LogHttp err = null;
        //if(!(api.getStatus() == 200)){
            ret.setDomaine(api.getDomain());
            ret.setIpClient(api.getIpClient());
            ret.setDate(api.getDate());
            ret.setRequete(api.getRequete());
            ret.setUrl(api.getUrl());
            ret.setStatus(api.getStatus());
            ret.setWeight(api.getWeigth());
            ret.setRessource("");
            return ret;
        //}
        //return err;
    }
}
