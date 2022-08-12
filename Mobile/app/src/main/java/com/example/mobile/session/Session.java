package com.example.mobile.session;

public class Session {
        static Object Session = null;

        public static Object getSession() {
            return Session;
        }

        public static void setSession(Object session) {
            Session = session;
        }
}
