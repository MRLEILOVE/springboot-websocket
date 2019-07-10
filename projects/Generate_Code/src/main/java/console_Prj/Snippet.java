package console_Prj;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Snippet {

    public static void main(String s[]) throws Exception {
        String url = "http://localhost:8080/corn-web-merchant/haha.json?name=abc";
//        url = "http://localhost:8080/web_Prj/haha?name=abc-1";
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setEntity(new StringEntity("{\"floginName\":\"abc-1\",\"ab\":\"123\"}"));

        CloseableHttpResponse response = httpClient.execute(httpPost);
//        Header[] hArr = response.getAllHeaders();
//        for (int i = 0; i < hArr.length; i++) {
//            Header h = hArr[i];
//            System.out.println(h.getName());
//            System.out.println(h.getValue());
//        }
        
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8"); 
        System.out.println(responseContent);

        response.close();
        httpClient.close();
        System.out.println("responseContent=" + responseContent);
    }
}
