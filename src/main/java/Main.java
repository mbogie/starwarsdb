import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request;
        Response response;
        ObjectMapper objectMapper = new ObjectMapper();
        boolean proper = false;
        boolean properF = false;

       /*// request = new Request.Builder().url("https://swapi.co/api/people/").build();
        request = new Request.Builder().url("http://httpbin.org/ip").build();
        response = client.newCall(request).execute();

        if(response.code()>=200 && response.code()<300) {
            System.out.println(response.code());
            //System.out.println(response.body().string());
            System.out.println(response.message());
            Ip ip = objectMapper.readValue(response.body().string(), Ip.class);

            System.out.println(ip.getOrigin());
        } else {
            System.out.println(response.message());
        }*/

        Scanner sc = new Scanner(System.in);
        int number = 0;
        while(!proper) {
            System.out.println("Podaj liczbe postaci do wyswietlenia z zakresu 1-88");
            number = sc.nextInt();
            if(number<1 || number >88){
                System.out.println("Liczba z poza zakresu");
            } else {
                proper = true;
            }
        }

        request = new Request.Builder().url("https://swapi.co/api/people/"+number+"/").build();
        response = client.newCall(request).execute();
        People people = objectMapper.readValue(response.body().string(), People.class);

        System.out.println("Ktory film chcesz wyswietlic dla "+people.getName());

        String[] peopleFilms = people.getFilms();
        for (int i = 0; i < peopleFilms.length; i++) {
            System.out.println(i+1+": " + peopleFilms[i]);
        }
        int filmNumber = 0;
        while (!properF){
            System.out.println("Podaj index filmu");
            filmNumber = sc.nextInt();
            if(filmNumber<1 || filmNumber >peopleFilms.length){
                System.out.println("Liczba z poza zakresu");
            } else {
                properF = true;
            }
        }

        request = new Request.Builder().url(peopleFilms[filmNumber-1]).build();
        response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
}
