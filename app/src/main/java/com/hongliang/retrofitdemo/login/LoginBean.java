package com.hongliang.retrofitdemo.login;

import java.util.List;

public class LoginBean {

    /**
     * data : {"password":"","chapterTops":[],"icon":"","admin":false,"collectIds":[],"id":25616,"type":0,"email":"","token":"","username":"13720232954"}
     * errorCode : 0
     * errorMsg :
     */





        /**
         * password :
         * chapterTops : []
         * icon :
         * admin : false
         * collectIds : []
         * id : 25616
         * type : 0
         * email :
         * token :
         * username : 13720232954
         */
        private String password;
        private List<?> chapterTops;
        private String icon;
        private boolean admin;
        private List<?> collectIds;
        private int id;
        private int type;
        private String email;
        private String token;
        private String username;

        public void setPassword(String password) {
            this.password = password;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public void setCollectIds(List<?> collectIds) {
            this.collectIds = collectIds;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public String getIcon() {
            return icon;
        }

        public boolean isAdmin() {
            return admin;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getEmail() {
            return email;
        }

        public String getToken() {
            return token;
        }

        public String getUsername() {
            return username;
        }
}
