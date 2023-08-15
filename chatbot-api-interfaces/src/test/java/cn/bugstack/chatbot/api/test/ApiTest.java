package cn.bugstack.chatbot.api.test;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/*
单元测试
 */



@Test
public class ApiTest {

    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //httpClient用于封装数据信息
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15552182821842/topics?scope=all&count=20");
        //添加cookie信息
        get.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22422251542244558%22%2C%22first_id%22%3A%22183544eaca279e-0cb6d1278a91f2-78565473-2073600-183544eaca316bc%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgzNTQ0ZWFjYTI3OWUtMGNiNmQxMjc4YTkxZjItNzg1NjU0NzMtMjA3MzYwMC0xODM1NDRlYWNhMzE2YmMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MjIyNTE1NDIyNDQ1NTgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22422251542244558%22%7D%2C%22%24device_id%22%3A%22183544eaca279e-0cb6d1278a91f2-78565473-2073600-183544eaca316bc%22%7D; zsxqsessionid=ceac525f42df4615390009f698312c87; abtest_env=product; zsxq_access_token=055C87E6-B2F5-5D90-1C72-B875081DD29C_68C04F231B801B44");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        //请求的信息是否是正确的信息
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/188542224255242/comments");

        post.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22422251542244558%22%2C%22first_id%22%3A%22183544eaca279e-0cb6d1278a91f2-78565473-2073600-183544eaca316bc%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgzNTQ0ZWFjYTI3OWUtMGNiNmQxMjc4YTkxZjItNzg1NjU0NzMtMjA3MzYwMC0xODM1NDRlYWNhMzE2YmMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MjIyNTE1NDIyNDQ1NTgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22422251542244558%22%7D%2C%22%24device_id%22%3A%22183544eaca279e-0cb6d1278a91f2-78565473-2073600-183544eaca316bc%22%7D; zsxqsessionid=ceac525f42df4615390009f698312c87; abtest_env=product; zsxq_access_token=055C87E6-B2F5-5D90-1C72-B875081DD29C_68C04F231B801B44");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        //补充数据信息
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        //回答，封装入参信息
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        //加实体对象
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        String pro = "127.0.0.1";//本机地址
        int pro1 = 10809; //代理端口号
        //创建一个 HttpHost 实例，这样就设置了代理服务器的主机和端口。
        HttpHost httpHost = new HttpHost(pro, pro1);
        //创建一个 RequestConfig 对象，然后使用 setProxy() 方法将代理 httpHost 设置进去。
        RequestConfig build = RequestConfig.custom().setProxy(httpHost).build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");
        //将 build 配置设置到 post 请求中包括先前指定的代理设置。
        post.setConfig(build);

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}