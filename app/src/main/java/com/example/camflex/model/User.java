package com.example.camflex.model;

public class User {
    private Long id;
    private String name;
    private String email;

    // 기본 생성자 (필수)
    public User() {}

    // 생성자
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter & setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}


//package com.example.camflex.model;
//
//public class User {
//    private Long id;
//    private String username;
//    private String password;
//
//    // 기본 생성자 필요 (Retrofit/Gson용)
//    public User() {}
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    // getter & setter
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//}
