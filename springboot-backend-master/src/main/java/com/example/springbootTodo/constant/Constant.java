package com.example.springbootTodo.constant;

public final class Constant {
    public static final String MONGODB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'";

    public static class TodoService {
        public static final String TODO_COLLECTION = "todos";
    }

    public static class SwaggerConfig {
        public static final String TITLE = "Pearson Prep | Todo Service";
        public static final String DESCRIPTION = "User Daily Todo list";
        public static final String TERMS_OF_SERVICE_URL = "";
        public static final String CONTACT_NAME = "Pearson Prep Team";
        public static final String CONTACT_URL = "https://www.pearson.com/us/higher-education/products-services-teaching/learning-engagement-tools/pearson-prep/for-students.html";
        public static final String CONTACT_EMAIL = "thisarathathsarani.ruwanpathirana@pearson.com";
        public static final String LICENSE = "Apache License Version 2.0";
        public static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";

        private SwaggerConfig() {
            // This is to make sure that no one will instantiate this class
        }
    }
}
