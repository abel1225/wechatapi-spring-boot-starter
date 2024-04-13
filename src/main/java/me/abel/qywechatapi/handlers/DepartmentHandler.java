package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.utils.HttpClientUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DepartmentHandler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public DepartmentHandler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public String getDepartments (String departId, String appType) throws IOException {
        String departmentsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.DEPARTMENTS);
        departmentsUrl = departmentsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        if (!"".equals(departId)) {
            departmentsUrl = departmentsUrl.replaceAll("ID", departId);
        }
        return httpClientUtil.getResponce(departmentsUrl);
    }

    public String departmentDelete (String departId, String appType) throws IOException {
        String departmentsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.DEPARTMENT_DELETE);
        departmentsUrl = departmentsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        if (!"".equals(departId)) {
            departmentsUrl = departmentsUrl.replaceAll("ID", departId);
        }
        return httpClientUtil.getResponce(departmentsUrl);
    }

    /**
     *
     * @param name       部门名称
     * @param parentId   父部门ID
     * @return
     */
    public String departmentCreate (String name, String parentId, String appType) throws IOException {
        String departmentsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.DEPARTMENT_CREATE);
        departmentsUrl = departmentsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("parentid", parentId);
        params.put("order", "1");
        return httpClientUtil.postJson(departmentsUrl, JSON.toJSONString(params));
    }

    /**
     *
     * @param name       部门名称
     * @param parentId   父部门ID
     * @param order      部门顺序号
     * @param departId   部门ID
     * @return
     */
    public String departmentUpdate (String name, String parentId, int order, String departId, String appType) throws IOException {
        String departmentsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.DEPARTMENT_UPDATE);
        departmentsUrl = departmentsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));

        Map<String, String> params = new HashMap<>();
        if (!"".equals(name)) {
            params.put("name", name);
        }
        if (!"".equals(parentId)) {
            params.put("parentid", parentId);
        }
        params.put("order", String.valueOf(order));
        params.put("id", departId);
        return httpClientUtil.postJson(departmentsUrl, JSON.toJSONString(params));
    }
}
