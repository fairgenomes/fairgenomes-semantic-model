package org.fairgenomes.ecosystem.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fairgenomes.ecosystem.datastructures.Mapping;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIConnector {

    private URL url;
    private List<Map<String, Object>> data;

    public APIConnector(String url) throws IOException {
        this.url = new URL(url);
        this.data = getData();
    }

    private List<Map<String, Object>> getData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(url, Map.class);
        List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("items"); //TODO how to make generic?
        return data;
    }

    public List<HashMap<String,Object>> findValuesFor(List<Mapping> mappings) throws IOException {

        List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();

        for(int i = 0; i < data.size(); i++)
        {
            HashMap<String, Object> hm = new HashMap<>();

            for(Mapping m : mappings)
            {
                if(data.get(i).containsKey(m.source))
                {
                    hm.put(m.source, data.get(i).get(m.source));
                }
            }
            result.add(hm);
        }
        return result;
    }
}
