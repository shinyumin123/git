package com.cgv.CgvProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MemberDTO{

    @Data
    public static class MemberCreateRequest{
        private String userId;
        private String password;
        private String name;
        private String number;
    }

    @Data
    public static class MemberLoginRequest {
        private String userId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class MemberResponse {
        private String userId;
        private String name;
    }


    @Data
    public static class MemberDeleteRequest{
        private String token;
    }

}
