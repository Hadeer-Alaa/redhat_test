package com.example.task.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Mahmoud.Aref - maxmya
 * Created At: 4/7/2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse {

    @NotNull
    private String code;

    @NotNull
    private String message;

    private Map<String, Object> additional;
}
