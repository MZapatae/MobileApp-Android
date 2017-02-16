package cl.mzapatae.mobileApp.datamodel.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 17-01-17
 * E-mail: miguel.zapatae@gmail.com
 */


public class CelmedianosResponse {
    @SerializedName("meta") private MetaResponse metaResponse;
    @SerializedName("celmedianos") private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public MetaResponse getMetaResponse() {
        return metaResponse;
    }

    public void setMetaResponse(MetaResponse metaResponse) {
        this.metaResponse = metaResponse;
    }

    public class User {
        @SerializedName("id_celmediano") private int id;
        @SerializedName("nombre") private String name;
        @SerializedName("email") private String email;
        @SerializedName("edad") private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
