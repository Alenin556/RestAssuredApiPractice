package api;

import groovy.util.logging.Slf4j;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@Slf4j
public class ReqresTest {

    private final static String URL = "https://reqres.in";
    private static String email = "@reqres.in";
    @Test
    public void checkAvatarAndIdConspectTest() {
        // GET запросы

        //Необходимо получить список пользователей

        // Объявляя List<UserData> users мы говорим что результат выполнения запроса мы хотим сохранить в список,
        // который принимает в себя объекты типа UserData,
        // а UserData создаст объект на основе Pojo класса, где указаны поля в соответствии с json
        List<UserData> users =
                // ключевое слово для выполнения запроса
                given()
                //ключевое слово when()
                .when() // слово после которого, мы указываем параметры при которых мы будем работать
                .contentType(ContentType.JSON) // указываем обрабатываемый тип данных, в нашем случае это JSON

                // указываем один из методов http (get,post,put,patch,delete)
                .get(URL + "/api/users?page=2") // указываем ссылку на ресурс с которым мы хотим общаться, получать и отправлять запросы
                //ключевое слово then() указывается перед тем как указать что необходимо сделать с полученными данными
                .then().log().all() // выводим логи полученного ответа

                        // ИЗВЛЕЧЕНИЕ
                        // Мы хотим извлечь данные в pojo класс чтобы произошло сохранение объекта,
                        // чтобы получить объекты из определенного массива данных, необходимо указать путь в getList() методе.
                        // Путем, будет считаться название переменной после которой, идет массив с объектами.
                        // Затем, вторым параметром нужно указать класс в который будут сохранены, извлечены данные ответа.
                .extract().body().jsonPath().getList("data", UserData.class);

        //ОБРАБОТКА
        //Мы хотим обработать данные и отфильтровать данные, поэтому воспользуемся обработкой потоков stream()
        // x -> используется как переменная для перебора которая сохранит в себе указанное поле.
        // т.е. x -> Assert.assertTrue (говорит выполнить проверку для всех) у кого строка аватар соответствует номеру id
        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        //Проверить что email пользователей оканчивается на @reqres.in
        // users.stream().allMatch()
        // используем поток stream() для обработки, метод allMatch() выполнит проверку по всем пользователям, если будет найдено одно несовпадение вернется false
        // x-> x.getEmail().endsWith(email)) , происходит перебор полей по email
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(email)));


        // Второй способ обработки данных
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        // преобразовываем id в строку x->x.getId().toString()
        List<String> id = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        for(int i =0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(id.get(i)));
        }
    }

    @Test
    public void checkAvatarAndIdTest1() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(email)));
    }

    @Test
    public void checkAvatarAndIdTest2OK200() {
        //Мы создали специальный класс в котором прописаны необходимые метод по установке соединения
        //И метод по проверке статуса ответа сервера

        //Теперь мы можем убрать ненужные строки так как они у нас уже содержаться в классах спецификациях
        // *смотри тест выше*

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        List<UserData> users = given()
                .when()
                .get( "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(email)));
    }

    @Test
    public void checkAvatarAndIdTest3Check400() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNOTFOUND400());

        List<UserData> users = given()
                .when()
                .get( "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(email)));
    }

    @Test
    public void outputGetNamesTest() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(x -> System.out.println(x.getFirst_name() + " " + x.getLast_name()));
    }


    //POST ЗАПРОСЫ
    @Test
    public void successRegTest(){

    }
}
