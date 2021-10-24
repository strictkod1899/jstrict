package ru.strict.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import ru.strict.validate.CommonValidate;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NetUtil {

    /**
     * Проверить соединение с сайтом
     */
    public static boolean checkConnect(String url) {
        return checkConnect(url, 3000);
    }

    /**
     * Проверить соединение с сайтом
     */
    public static boolean checkConnect(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Получить html страницу по url в кодировке UTF-8
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGetRequestUTF8(String url) throws IOException {
        return sendGetRequest(url, null, "UTF-8");
    }

    /**
     * Получить html страницу по url в кодировке UTF-8
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGetRequestUTF8(String url, Map<String, String> parameters) throws IOException {
        return sendGetRequest(url, parameters, "UTF-8");
    }

    /**
     * Получить html страницу по url
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGetRequest(String url, Map<String, String> parameters, String encode) throws IOException {
        if (parameters != null && parameters.size() > 0) {
            String[] stringParameters = parameters.keySet().stream()
                    .map(key -> key + "=" + parameters.get(key))
                    .toArray(String[]::new);
            String parametersLine = StringUtil.join("&", stringParameters);

            if (!CommonValidate.isEmptyOrNull(parametersLine)) {
                url += "?" + parametersLine;
            }
        }

        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        // optional default is GET
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        String result = null;
        if (responseCode == 200) {
            if (CommonValidate.isEmptyOrNull(encode)) {
                encode = "UTF-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encode));
            StringBuilder pageContent = new StringBuilder();
            while (reader.ready()) {
                pageContent.append(new String(reader.readLine()));
            }
            reader.close();
            result = pageContent.toString();
        }

        return result;
    }

    /**
     * Отправить post-запрос на указанный адрес используя кодировку UTF-8
     *
     * @return Возвращается строкове представление ответа на запрос
     * @throws IOException
     */
    public static String sendPostRequestUTF8(String url) throws IOException {
        return sendPostRequest(url, null, "UTF-8");
    }

    /**
     * Отправить post-запрос на указанный адрес используя кодировку UTF-8
     *
     * @return Возвращается строкове представление ответа на запрос
     * @throws IOException
     */
    public static String sendPostRequestUTF8(String url, Map<String, String> parameters) throws IOException {
        return sendPostRequest(url, parameters, "UTF-8");
    }

    /**
     * Отправить post-запрос на указанный адрес.
     *
     * @return Возвращается строкове представление ответа на запрос
     * @throws IOException
     */
    public static String sendPostRequest(String url, Map<String, String> parameters, String encode) throws IOException {
        if (CommonValidate.isEmptyOrNull(encode)) {
            encode = "UTF-8";
        }

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost(url);

        List<NameValuePair> params = parameters == null
                ? new ArrayList<>()
                : parameters.keySet().stream()
                .map(key -> new BasicNameValuePair(key, parameters.get(key)))
                .collect(Collectors.toList());

        postRequest.setEntity(new UrlEncodedFormEntity(params, encode));

        HttpResponse response = httpClient.execute(postRequest);
        HttpEntity entity = response.getEntity();

        String result = null;

        if (entity != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), encode))) {
                StringBuilder pageContent = new StringBuilder();
                while (reader.ready()) {
                    pageContent.append(new String(reader.readLine()));
                }
                result = pageContent.toString();
            }
        }

        return result;
    }
}
