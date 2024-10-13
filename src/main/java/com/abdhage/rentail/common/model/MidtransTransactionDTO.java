package com.abdhage.rentail.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MidtransTransactionDTO {

    // Required Transaction Details
    private String orderId;
    private Double grossAmount;

    // Optional Fields
    private List<ItemDetail> itemDetails;
    private CustomerDetail customerDetails;
    private List<String> enabledPayments;
    private Callback callbacks;
    private Expiry expiry;
    private String customField1;
    private String customField2;
    private String customField3;
    private Recurring recurring;

    public Map<String, Object> toMap() {
        Map<String, Object> dto = new HashMap<>();

        dto.put("order_id", orderId);
        dto.put("gross_amount", grossAmount);

        if (itemDetails != null && !itemDetails.isEmpty())
            dto.put("item_details", itemDetails);
        if (customerDetails != null)
            dto.put("customer_details", customerDetails);
        if (enabledPayments != null && !enabledPayments.isEmpty())
            dto.put("enabled_payments", enabledPayments);
        if (callbacks != null)
            dto.put("callbacks", callbacks);
        if (expiry != null)
            dto.put("expiry", expiry);
        if (customField1 != null)
            dto.put("custom_field1", customField1);
        if (customField2 != null)
            dto.put("custom_field2", customField2);
        if (customField3 != null)
            dto.put("custom_field3", customField3);
        if (recurring != null)
            dto.put("recurring", recurring);

        return dto;
    }

    // Nested classes for optional structures

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemDetail {
        private String id;
        private String name;
        private Integer quantity;
        private Double price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerDetail {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Callback {
        private String finishUrl;
        private String unfinishUrl;
        private String errorUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Expiry {
        private Integer duration;
        private String unit;
        private String pageExpiry;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Recurring {
        private Boolean enabled;
        private String interval;
        private Integer intervalCount;
    }
}
