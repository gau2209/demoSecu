package com.gau.security.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {
    private String id;
    private String userName;
    private String phone;
    private String passWord;
}
