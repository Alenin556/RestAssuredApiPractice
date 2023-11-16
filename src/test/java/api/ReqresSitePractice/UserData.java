package api.ReqresSitePractice;

public class UserData {

    //Pojo класс, который хранит в себе поля объекта data

    //Типы данных указываются с большой буквы
    //Имена полей должны соответствовать значениям json
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public UserData(){
    }

    //Конструктор необходим для создания объектов после получения ответа от сервера, для извлечения и сохранения данных
    public UserData(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    //нам необходимы методы для получения информации из полей
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }

}
