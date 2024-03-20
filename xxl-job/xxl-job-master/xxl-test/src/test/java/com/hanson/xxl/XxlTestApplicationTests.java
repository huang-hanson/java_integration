package com.hanson.xxl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = XxlTestApplication.class)
@RunWith(SpringRunner.class)
class XxlTestApplicationTests {

    private static final String COOKIES = "XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531306164633339343962613539616262653536653035376632306638383365222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d; Path=/; HttpOnly";

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Autowired
    private RestTemplate restTemplate;

    /* Cookie 是根据用户名密码生成的，基本不变，可直接保存数据库或者Redis，然后读取即不必反复登录 */
    /* Cookie 如果后期Cookie有失效时间了，则可用定时任务定时刷新或者失效重登重新保存即可 */

    /**
     * 模拟登录并拿到Cookie
     */
    @Test
    public void login() {
        HttpHeaders httpHeaders = new HttpHeaders();
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userName", "admin");
        map.add("password", "123456");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/login", request, String.class);
        System.out.println(response.getHeaders().get("Set-Cookie").get(0));  //   XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531306164633339343962613539616262653536653035376632306638383365222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d; Path=/; HttpOnly

    }

    /*   组操作---> 对执行器进行操作  */

    /**
     * 保存组Group
     */
    @Test
    public void saveGroup() {
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("appname", "xxl-job-executor-sample-hz");        //应用名称
        map.add("title", "测试执行器");      //执行器名称
        map.add("order", "1");          //排序方式
        map.add("addressType", "1");        //注册方式 ：  0为
        map.add("addressList", "localhost:8081,localhost:8081");          //多地址逗号分隔
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobgroup/save", request, String.class);
        System.out.println(response.getBody());        // {"code":200,"msg":null,"content":null}   返回此，且数据库增加数据即为成功
    }

    /**
     * 修改组
     */
    @Test
    public void updateGroup(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","5");          //修改的，id一定不能为空
        map.add("appname", "xxl-job-executor-sample-hz");        //应用名称
        map.add("title", "测试执行器134");      //执行器名称
        map.add("order", "1");          //排序方式
        map.add("addressType", "1");        //注册方式 ：  0为
        map.add("addressList", "localhost:8081");          //多地址逗号分隔
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobgroup/update", request, String.class);
        System.out.println(response.getBody());         //{"code":200,"msg":null,"content":null}
    }

    /**
     * 删除组
     */
    @Test
    public void removeGroup(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","4");          //删除的，id一定不能为空
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobgroup/remove", request, String.class);
        System.out.println(response.getBody());         //{"code":200,"msg":null,"content":null}
    }

    /* 定时任务操作：查询，新增,编辑，启动，停止，删除等*/
    /**
     * 获取指定的执行器下的任务列表
     */
    @Test
    public void pageList(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("jobGroup", "1");
        map.add("triggerStatus", "-1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/pageList", request, String.class);
        System.out.println(response.getBody());            // {"recordsFiltered":5,"data":[{"id":5,"jobGroup":1,"jobDesc":"命令行任务","addTime":"2024-03-20T09:01:44.000+00:00","updateTime":"2024-03-20T09:07:56.000+00:00","author":"hanson","alarmEmail":"","scheduleType":"FIX_RATE","scheduleConf":"3","misfireStrategy":"DO_NOTHING","executorRouteStrategy":"FIRST","executorHandler":"commandJobHandler2","executorParam":"calc","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":0,"executorFailRetryCount":0,"glueType":"BEAN","glueSource":"","glueRemark":"GLUE代码初始化","glueUpdatetime":"2024-03-20T09:01:44.000+00:00","childJobId":"","triggerStatus":1,"triggerLastTime":1710942949734,"triggerNextTime":1710942952734},{"id":4,"jobGroup":1,"jobDesc":"测试分片任务","addTime":"2024-03-20T09:00:54.000+00:00","updateTime":"2024-03-20T09:00:54.000+00:00","author":"hanson","alarmEmail":"","scheduleType":"CRON","scheduleConf":"0 0 0 * * ? *","misfireStrategy":"DO_NOTHING","executorRouteStrategy":"SHARDING_BROADCAST","executorHandler":"shardingJobHandler","executorParam":"","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":0,"executorFailRetryCount":0,"glueType":"BEAN","glueSource":"","glueRemark":"GLUE代码初始化","glueUpdatetime":"2024-03-20T09:00:54.000+00:00","childJobId":"","triggerStatus":0,"triggerLastTime":0,"triggerNextTime":0},{"id":3,"jobGroup":1,"jobDesc":"testGLUE","addTime":"2024-03-20T08:14:29.000+00:00","updateTime":"2024-03-20T08:15:33.000+00:00","author":"hanson","alarmEmail":"","scheduleType":"CRON","scheduleConf":"0/1 * * * * ?","misfireStrategy":"DO_NOTHING","executorRouteStrategy":"FIRST","executorHandler":"","executorParam":"","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":0,"executorFailRetryCount":0,"glueType":"GLUE_GROOVY","glueSource":"package com.xxl.job.service.handler;\r\n\r\nimport com.xxl.job.core.context.XxlJobHelper;\r\nimport com.xxl.job.core.handler.IJobHandler;\r\n\r\npublic class DemoGlueJobHandler extends IJobHandler {\r\n\r\n\t@Override\r\n\tpublic void execute() throws Exception {\r\n\t\tXxlJobHelper.log(\"XXL-JOB, Hello World.\");\r\n\t}\r\n\r\n}\r\n","glueRemark":"GLUE代码初始化","glueUpdatetime":"2024-03-20T08:14:29.000+00:00","childJobId":"","triggerStatus":0,"triggerLastTime":0,"triggerNextTime":0},{"id":2,"jobGroup":1,"jobDesc":"test","addTime":"2024-03-20T06:52:08.000+00:00","updateTime":"2024-03-20T06:55:37.000+00:00","author":"hanson","alarmEmail":"","scheduleType":"CRON","scheduleConf":"0/2 * * * * ?","misfireStrategy":"DO_NOTHING","executorRouteStrategy":"FIRST","executorHandler":"myJobHandler","executorParam":"","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":0,"executorFailRetryCount":0,"glueType":"BEAN","glueSource":"","glueRemark":"GLUE代码初始化","glueUpdatetime":"2024-03-20T06:52:08.000+00:00","childJobId":"","triggerStatus":1,"triggerLastTime":1710942950000,"triggerNextTime":1710942952000},{"id":1,"jobGroup":1,"jobDesc":"测试任务1","addTime":"2018-11-03T14:21:31.000+00:00","updateTime":"2018-11-03T14:21:31.000+00:00","author":"XXL","alarmEmail":"","scheduleType":"CRON","scheduleConf":"0 0 0 * * ? *","misfireStrategy":"DO_NOTHING","executorRouteStrategy":"FIRST","executorHandler":"demoJobHandler","executorParam":"","executorBlockStrategy":"SERIAL_EXECUTION","executorTimeout":0,"executorFailRetryCount":0,"glueType":"BEAN","glueSource":"","glueRemark":"GLUE代码初始化","glueUpdatetime":"2018-11-03T14:21:31.000+00:00","childJobId":"","triggerStatus":0,"triggerLastTime":0,"triggerNextTime":0}],"recordsTotal":5}
    }

    /**
     * 增加定时任务配置
     */
    @Test
    public void addInfo(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("jobGroup","8");        //执行器主键id
        map.add("scheduleConf","0/1 * * * * ? ");        //表达式
        map.add("jobDesc","测试任务我是最新的测试任务啊啊啊啊啊啊");         //任务描述
        map.add("author","hanson");           //负责人
        map.add("alarmEmail","115865683@qq.com");     //报警邮件
        map.add("scheduleType","CRON");  // 调度类型
        map.add("misfireStrategy","DO_NOTHING");     // 调度配置，值含义取决于调度类型
        map.add("executorRouteStrategy","FIRST");            //执行器路由策略
        map.add("executorHandler","测试JobHandler2");              //执行器，任务Handler名称
        map.add("executorParam","121454");            //执行器，任务参数
        map.add("executorBlockStrategy","SERIAL_EXECUTION");        //阻塞处理策略
        map.add("executorTimeout","101");          //任务执行超时时间，单位秒
        map.add("executorFailRetryCount","1");       //失败重试次数
        map.add("glueType","BEAN");                 //GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
        map.add("glueSource","");               //GLUE源代码
        map.add("glueRemark","GLUE代码初始化");               //GLUE备注
        map.add("childJobId","");               //子任务ID,多个逗号分隔
//        map.add("jobStatus","");                //任务状态 【base on quartz】
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/add", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":"15"}
    }

    /**
     * 修改定时任务配置
     */
    @Test
    public void updateInfo(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","7");             //注意：修改必须带主键
        map.add("jobGroup","8");        //执行器主键id
        map.add("scheduleConf","0/1 * * * * ? ");        //表达式
        map.add("scheduleType","CRON");  // 调度类型
        map.add("misfireStrategy","DO_NOTHING");     // 调度配置，值含义取决于调度类型
        map.add("jobDesc","测试任务我是最新的测试任务");         //任务描述
        map.add("author","hanson");           //负责人
        map.add("alarmEmail","115865683@qq.com");     //报警邮件
        map.add("executorRouteStrategy","FIRST");            //执行器路由策略
        map.add("executorHandler","测试JobHandler");              //执行器，任务Handler名称
        map.add("executorParam","121454");            //执行器，任务参数
        map.add("executorBlockStrategy","SERIAL_EXECUTION");        //阻塞处理策略
        map.add("executorTimeout","101");          //任务执行超时时间，单位秒
        map.add("executorFailRetryCount","1");       //失败重试次数
        map.add("glueType","BEAN");                 //GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
        map.add("glueSource","");               //GLUE源代码
        map.add("glueRemark","GLUE代码初始化");               //GLUE备注
        map.add("childJobId","");               //子任务ID,多个逗号分隔
//        map.add("jobStatus","");                //任务状态 【base on quartz】
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/update", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":null}
    }

    /**
     * 删除定时任务配置
     */
    @Test
    public void removeInfo(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","8");             //注意：删除必须带主键
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/remove", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":null}
    }


    /**
     * 启动定时任务
     */
    @Test
    public void startInfo(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","7");             //启动的任务id
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/start", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":null}
    }

    /**
     * 停止定时任务
     */
    @Test
    public void stopInfo(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","7");             //启动的任务id
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/stop", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":null}
    }

    /**
     * 执行一次定时任务
     */
    @Test
    public void startOne(){
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        /* 登录获取Cookie 这里是直接给Cookie，可使用下方的login方法拿到Cookie给入*/
        cookies.add(COOKIES);
        headers.put(HttpHeaders.COOKIE,cookies);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id","7");             //启动的任务id
        map.add("executorParam","13");             //启动的任务参数
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/xxl-job-admin/jobinfo/trigger", request, String.class);
        System.out.println(response.getBody());             //{"code":200,"msg":null,"content":null}
    }
}
