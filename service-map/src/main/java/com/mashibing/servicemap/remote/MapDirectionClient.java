package com.mashibing.servicemap.remote;

import com.mashibing.internalcommon.constant.AmapConfigConstants;
import com.mashibing.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDirectionClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装请求调用url
        /**
         * https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json&key=98dbc7b0e29e829ade7555bca6c32c74
         */
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin="+depLongitude+","+depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination="+destLongitude+","+destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key="+amapKey);
        log.info(urlBuild.toString());



        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString=directionEntity.getBody();
        log.info("高德地图，路径规划，返回信息："+directionString);

        //解析接口
        DirectionResponse directionResponse=parseDirectionEntity(directionString);


        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse=null;
        try {

            JSONObject result = JSONObject.fromObject(directionString);
            int status=result.getInt(AmapConfigConstants.STATUS);
            if (status==1){
                if (result.has(AmapConfigConstants.ROUTE)){
                    JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                    JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                    JSONObject pathsObject = pathsArray.getJSONObject(0);
                    directionResponse=new DirectionResponse();
                    if (pathsObject.has(AmapConfigConstants.DISTANCE)){
                        int distance = pathsObject.getInt(AmapConfigConstants.DISTANCE);
                        directionResponse.setDistance(distance);
                    }
                    if (pathsObject.has(AmapConfigConstants.DURATION)){
                        int duration = pathsObject.getInt(AmapConfigConstants.DURATION);
                        directionResponse.setDuration(duration);
                    }
                }
            }
        }catch (Exception e){

        }
        return directionResponse;
    }


}
